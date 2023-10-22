package com.example.memorypalace.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.memorypalace.R;
import com.example.memorypalace.activities.CreateNewRoom;
import com.example.memorypalace.activities.adapters.RoomsAdapter;
import com.example.memorypalace.activities.database.RoomsDatabase;
import com.example.memorypalace.activities.entities.Rooms;
import com.example.memorypalace.activities.listeners.RoomsListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RoomsListener {

    //room request codes
    public static final int REQUEST_CODE_ADD_ROOM = 1;
    public static final int REQUEST_CODE_UPDATE_ROOM = 2;
    public static final int REQUEST_CODE_VIEW_ROOMS = 3;

    //widgets
    private RecyclerView rooms_recycleView;
    private List<Rooms> roomList;
    private RoomsAdapter roomsAdapter;

    private int roomClickedPosition = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView addRoomImg = findViewById(R.id.add_room);

        addRoomImg.setOnClickListener(v -> startActivityForResult(
                new Intent(getApplicationContext(), CreateNewRoom.class),
                REQUEST_CODE_ADD_ROOM));

        rooms_recycleView = findViewById(R.id.rooms_recycleView);

        //sets rooms as list view
        rooms_recycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        roomList = new ArrayList<>();
        roomsAdapter = new RoomsAdapter(roomList, this);
        rooms_recycleView.setAdapter(roomsAdapter);

        getRooms(REQUEST_CODE_VIEW_ROOMS, false);

        //Searches typed room in main activity
        EditText search_input = findViewById(R.id.search_input);
        search_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                roomsAdapter.cancelTimer();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(roomList.size() != 0){
                    roomsAdapter.searchRooms(s.toString());
                }
            }
        });
    }

    //gets rooms title and date from database adapter
    private void getRooms(final int requestCode, final boolean isRoomDeleted){

        @SuppressLint("StaticFieldLeak")
        class GetRoomsTask extends AsyncTask<Void, Void, List<Rooms>> {

            @Override
            protected List<Rooms> doInBackground(Void... voids) {
                return RoomsDatabase.getDatabase(getApplicationContext()).roomDao().getAllRooms();
            }

            @Override
            protected void onPostExecute(List<Rooms> rooms) {
                super.onPostExecute(rooms);

                if(requestCode == REQUEST_CODE_VIEW_ROOMS){
                    roomList.addAll(rooms);
                    roomsAdapter.notifyDataSetChanged();
                } else if(requestCode == REQUEST_CODE_ADD_ROOM){
                    roomList.add(0, rooms.get(0));
                    roomsAdapter.notifyItemInserted(0);
                    rooms_recycleView.smoothScrollToPosition(0);
                } else if(requestCode == REQUEST_CODE_UPDATE_ROOM){
                    roomList.remove(roomClickedPosition);
                    if(isRoomDeleted){
                        roomsAdapter.notifyItemRemoved(roomClickedPosition);
                    } else{
                        roomList.add(roomClickedPosition, rooms.get(roomClickedPosition));
                        roomsAdapter.notifyItemChanged(roomClickedPosition);
                    }
                }
            }
        }

        new GetRoomsTask().execute();
    }

    @Override
    public void onRoomClicked(Rooms rooms, int position) {
        roomClickedPosition = position;
        Intent intent = new Intent(getApplicationContext(), CreateNewRoom.class);
        intent.putExtra("isViewOrUpdate", true);
        intent.putExtra("room", rooms);
        startActivityForResult(intent, REQUEST_CODE_UPDATE_ROOM);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_ADD_ROOM && resultCode == RESULT_OK){
            getRooms(REQUEST_CODE_ADD_ROOM, false);
        } else if (requestCode == REQUEST_CODE_UPDATE_ROOM && resultCode == RESULT_OK){
            if (data != null){
                getRooms(REQUEST_CODE_UPDATE_ROOM, data.getBooleanExtra("isRoomDeleted", false));
            }
        }
    }
}