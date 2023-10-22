package com.example.memorypalace.activities.adapters;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorypalace.R;
import com.example.memorypalace.activities.entities.Rooms;
import com.example.memorypalace.activities.listeners.RoomsListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

//recycle room adapter class
public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.RoomViewHolder>{

    private List<Rooms> roomsList;
    private RoomsListener roomsListener;
    private Timer timer;
    private List<Rooms> roomsSource;



    public RoomsAdapter(List<Rooms> roomsList,RoomsListener roomsListener) {
        this.roomsList = roomsList;
        this.roomsListener = roomsListener;
        roomsSource = roomsList;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RoomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_room,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        holder.setRoom(roomsList.get(position));
        holder.layoutRoom.setOnClickListener(v -> roomsListener.onRoomClicked(roomsList.get(position), position));
    }

    @Override
    public int getItemCount() {
        return roomsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class RoomViewHolder extends RecyclerView.ViewHolder{

        TextView title_text, text_dateTime;
        LinearLayout layoutRoom;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);

            title_text = itemView.findViewById(R.id.title_text);
            text_dateTime = itemView.findViewById(R.id.text_dateTime);
            layoutRoom = itemView.findViewById(R.id.layoutRoom);
        }

        void setRoom (Rooms rooms){
            title_text.setText(rooms.getTitle());
            text_dateTime.setText(rooms.getDateTime());
        }
    }

    public void searchRooms(final String searchKeyword){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(searchKeyword.trim().isEmpty()){
                    roomsList = roomsSource;
                } else {
                    ArrayList<Rooms> temp = new ArrayList<>();
                    for (Rooms rooms : roomsSource){
                        if(rooms.getTitle().toLowerCase().contains(searchKeyword.toLowerCase())){
                            temp.add(rooms);
                        }
                    }

                    roomsList = temp;
                }

                new Handler(Looper.getMainLooper()).post(() -> notifyDataSetChanged());
            }
        }, 500);
    }

    public void cancelTimer(){
        if(timer != null){
            timer.cancel();
        }
    }


}
