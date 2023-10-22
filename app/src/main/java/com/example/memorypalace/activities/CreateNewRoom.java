package com.example.memorypalace.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.memorypalace.R;
import com.example.memorypalace.activities.database.RoomsDatabase;
import com.example.memorypalace.activities.entities.Rooms;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateNewRoom extends AppCompatActivity {

    //camera and and storage permission code
    public static final int CAM_PERM_CODE = 1;
    public static final int STORAGE_PERM_CODE = 2;

    //camera and upload codes
    public static final int CAM_REQUEST_CODE = 3;
    public static final int UPLOAD_REQUEST_CODE = 4;

    public static final int CAM_REQUEST_CODE2 = 5;
    public static final int UPLOAD_REQUEST_CODE2 = 6;

    public static final int CAM_REQUEST_CODE3 = 7;
    public static final int UPLOAD_REQUEST_CODE3 = 8;

    public static final int CAM_REQUEST_CODE4 = 9;
    public static final int UPLOAD_REQUEST_CODE4 = 10;

    public static final int CAM_REQUEST_CODE5 = 11;
    public static final int UPLOAD_REQUEST_CODE5 = 13;

    public static final int CAM_REQUEST_CODE6 = 14;
    public static final int UPLOAD_REQUEST_CODE6 = 15;

    public static final int CAM_REQUEST_CODE7 = 16;
    public static final int UPLOAD_REQUEST_CODE7 = 17;

    public static final int CAM_REQUEST_CODE8 = 18;
    public static final int UPLOAD_REQUEST_CODE8 = 19;

    public static final int CAM_REQUEST_CODE9 = 20;
    public static final int UPLOAD_REQUEST_CODE9 = 21;

    public static final int CAM_REQUEST_CODE10 = 22;
    public static final int UPLOAD_REQUEST_CODE10 = 23;

    //Widgets
    ImageView image_insert, image_insert2, image_insert3, image_insert4, image_insert5, image_insert6,
            image_insert7,image_insert8, image_insert9, image_insert10;
    Button upload_btn, upload_btn2, upload_btn3, upload_btn4, upload_btn5, upload_btn6, upload_btn7,
            upload_btn8, upload_btn9, upload_btn10,
            camera_btn, camera_btn2, camera_btn3, camera_btn4, camera_btn5, camera_btn6, camera_btn7,
            camera_btn8, camera_btn9, camera_btn10;

    private EditText title_room, detail_progress, detail_progress2, detail_progress3, detail_progress4,
            detail_progress5, detail_progress6, detail_progress7, detail_progress8, detail_progress9,
            detail_progress10;

    private TextView date_time;

    //images file path
    String currentPhotoPath;
    String currentPhotoPath2;
    String currentPhotoPath3;
    String currentPhotoPath4;
    String currentPhotoPath5;
    String currentPhotoPath6;
    String currentPhotoPath7;
    String currentPhotoPath8;
    String currentPhotoPath9;
    String currentPhotoPath10;

    private Rooms alreadyExistingRoom;

    private AlertDialog dialogDeleteRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_room);

        //brings to main activity when back button is pressed
        ImageView imageBackProgress = findViewById(R.id.back_img);
        imageBackProgress.setOnClickListener(v -> onBackPressed());

        //saves room once clicked
        ImageView save_room = findViewById(R.id.save_room);
        save_room.setOnClickListener(v -> saveRoom());

        //xml ids
        title_room = findViewById(R.id.title_room);
        date_time = findViewById(R.id.date_time);
        detail_progress = findViewById(R.id.detail_progress);
        detail_progress2 = findViewById(R.id.detail_progress2);
        detail_progress3 = findViewById(R.id.detail_progress3);
        detail_progress4 = findViewById(R.id.detail_progress4);
        detail_progress5 = findViewById(R.id.detail_progress5);
        detail_progress6 = findViewById(R.id.detail_progress6);
        detail_progress7 = findViewById(R.id.detail_progress7);
        detail_progress8 = findViewById(R.id.detail_progress8);
        detail_progress9 = findViewById(R.id.detail_progress9);
        detail_progress10 = findViewById(R.id.detail_progress10);

        //Sets room creation date
        date_time.setText(new SimpleDateFormat("EEEE, dd MMMM yyyy",
                Locale.getDefault()).format(new Date()));

        //image upload 1
        image_insert = findViewById(R.id.image_insert);
        upload_btn = findViewById(R.id.upload_btn);
        camera_btn = findViewById(R.id.camera_btn);

        //on upload button clocked
        upload_btn.setOnClickListener(v -> requestStoragePermission());

        //on camera button clicked
        camera_btn.setOnClickListener(v -> requestCameraPermission());

        //image upload 2
        image_insert2 = findViewById(R.id.image_insert2);
        upload_btn2 = findViewById(R.id.upload_btn2);
        camera_btn2 = findViewById(R.id.camera_btn2);

        //on upload button 2 clicked
        upload_btn2.setOnClickListener(v -> {
            Intent upload = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            if(upload.resolveActivity(getPackageManager()) != null){
                startActivityForResult(upload, UPLOAD_REQUEST_CODE2);
            }
        });

        //on camera button 2 clicked
        camera_btn2.setOnClickListener(v -> dispatchTakePictureIntent2());

        //image upload 3
        image_insert3 = findViewById(R.id.image_insert3);
        upload_btn3 = findViewById(R.id.upload_btn3);
        camera_btn3 = findViewById(R.id.camera_btn3);

        //on upload button 3 clicked
        upload_btn3.setOnClickListener(v -> {
            Intent upload = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            if(upload.resolveActivity(getPackageManager()) != null){
                startActivityForResult(upload, UPLOAD_REQUEST_CODE3);
            }
        });

        //on camera button 3 clicked
        camera_btn3.setOnClickListener(v -> dispatchTakePictureIntent3());

        //image upload 4
        image_insert4 = findViewById(R.id.image_insert4);
        upload_btn4 = findViewById(R.id.upload_btn4);
        camera_btn4 = findViewById(R.id.camera_btn4);

        //on upload button 4 clicked
        upload_btn4.setOnClickListener(v -> {
            Intent upload = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            if(upload.resolveActivity(getPackageManager()) != null){
                startActivityForResult(upload, UPLOAD_REQUEST_CODE4);
            }
        });

        //on camera button 4 clicked
        camera_btn4.setOnClickListener(v -> dispatchTakePictureIntent4());

        //image upload 5
        image_insert5 = findViewById(R.id.image_insert5);
        upload_btn5 = findViewById(R.id.upload_btn5);
        camera_btn5 = findViewById(R.id.camera_btn5);

        //on upload button 5 clicked
        upload_btn5.setOnClickListener(v -> {
            Intent upload = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            if(upload.resolveActivity(getPackageManager()) != null){
                startActivityForResult(upload, UPLOAD_REQUEST_CODE5);
            }
        });

        //on camera button 5 clicked
        camera_btn5.setOnClickListener(v -> dispatchTakePictureIntent5());

        //image upload 6
        image_insert6 = findViewById(R.id.image_insert6);
        upload_btn6 = findViewById(R.id.upload_btn6);
        camera_btn6 = findViewById(R.id.camera_btn6);

        //on upload button 6 clicked
        upload_btn6.setOnClickListener(v -> {
            Intent upload = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            if(upload.resolveActivity(getPackageManager()) != null){
                startActivityForResult(upload, UPLOAD_REQUEST_CODE6);
            }

        });

        //on camera button 6 clicked
        camera_btn6.setOnClickListener(v -> dispatchTakePictureIntent6());

        //image upload 7
        image_insert7 = findViewById(R.id.image_insert7);
        upload_btn7 = findViewById(R.id.upload_btn7);
        camera_btn7 = findViewById(R.id.camera_btn7);

        //on upload button 7 clicked
        upload_btn7.setOnClickListener(v -> {
            Intent upload = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            if(upload.resolveActivity(getPackageManager()) != null){
                startActivityForResult(upload, UPLOAD_REQUEST_CODE7);
            }
        });

        //on camera button 7 clicked
        camera_btn7.setOnClickListener(v -> dispatchTakePictureIntent7());

        //image upload 8
        image_insert8 = findViewById(R.id.image_insert8);
        upload_btn8 = findViewById(R.id.upload_btn8);
        camera_btn8 = findViewById(R.id.camera_btn8);

        //on upload button 8 clicked
        upload_btn8.setOnClickListener(v -> {
            Intent upload = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            if(upload.resolveActivity(getPackageManager()) != null){
                startActivityForResult(upload, UPLOAD_REQUEST_CODE8);
            }
        });

        //on camera button 8 clicked
        camera_btn8.setOnClickListener(v -> dispatchTakePictureIntent8());

        //image upload 9
        image_insert9 = findViewById(R.id.image_insert9);
        upload_btn9 = findViewById(R.id.upload_btn9);
        camera_btn9 = findViewById(R.id.camera_btn9);

        //on upload button 9 clicked
        upload_btn9.setOnClickListener(v -> {
            Intent upload = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            if(upload.resolveActivity(getPackageManager()) != null){
                startActivityForResult(upload, UPLOAD_REQUEST_CODE9);
            }
        });

        //on camera button 9 clicked
        camera_btn9.setOnClickListener(v -> dispatchTakePictureIntent9());

        //image upload 10
        image_insert10 = findViewById(R.id.image_insert10);
        upload_btn10 = findViewById(R.id.upload_btn10);
        camera_btn10= findViewById(R.id.camera_btn10);

        //on upload button 10 clicked
        upload_btn10.setOnClickListener(v -> {
            Intent upload = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            if(upload.resolveActivity(getPackageManager()) != null){
                startActivityForResult(upload, UPLOAD_REQUEST_CODE10);
            }
        });

        //on camera button 10 clicked
        camera_btn10.setOnClickListener(v -> dispatchTakePictureIntent10());

        //already created room
        if(getIntent().getBooleanExtra("isViewOrUpdate", false)){
            alreadyExistingRoom = (Rooms) getIntent().getSerializableExtra("room");
            setViewOrUpdateRoom();
        }

        if (alreadyExistingRoom != null){
            LinearLayout deleteLinearLayout = findViewById(R.id.deleteRoomLayout);
            deleteLinearLayout.setVisibility(View.VISIBLE);
            deleteLinearLayout.setOnClickListener(v -> showDeleteRoomDialog());

        }
    }

    //sets previously created room data
    private void setViewOrUpdateRoom(){
        title_room.setText(alreadyExistingRoom.getTitle());
        date_time.setText(alreadyExistingRoom.getDateTime());
        detail_progress.setText(alreadyExistingRoom.getDesc1());
        detail_progress2.setText(alreadyExistingRoom.getDesc2());
        detail_progress3.setText(alreadyExistingRoom.getDesc3());
        detail_progress4.setText(alreadyExistingRoom.getDesc4());
        detail_progress5.setText(alreadyExistingRoom.getDesc5());
        detail_progress6.setText(alreadyExistingRoom.getDesc6());
        detail_progress7.setText(alreadyExistingRoom.getDesc7());
        detail_progress8.setText(alreadyExistingRoom.getDesc8());
        detail_progress9.setText(alreadyExistingRoom.getDesc9());
        detail_progress10.setText(alreadyExistingRoom.getDesc10());

        //checks if images were inserted and sets the image path from the database
        if(alreadyExistingRoom.getImage1() != null && !alreadyExistingRoom.getImage1().trim().isEmpty()){
            image_insert.setImageBitmap(BitmapFactory.decodeFile(alreadyExistingRoom.getImage1()));
            image_insert.setVisibility(View.VISIBLE);
            currentPhotoPath = alreadyExistingRoom.getImage1();
        }

        if(alreadyExistingRoom.getImage2() != null && !alreadyExistingRoom.getImage2().trim().isEmpty()){
            image_insert2.setImageBitmap(BitmapFactory.decodeFile(alreadyExistingRoom.getImage2()));
            image_insert2.setVisibility(View.VISIBLE);
            currentPhotoPath2 = alreadyExistingRoom.getImage2();
        }

        if(alreadyExistingRoom.getImage3() != null && !alreadyExistingRoom.getImage3().trim().isEmpty()){
            image_insert3.setImageBitmap(BitmapFactory.decodeFile(alreadyExistingRoom.getImage3()));
            image_insert3.setVisibility(View.VISIBLE);
            currentPhotoPath3 = alreadyExistingRoom.getImage3();
        }

        if(alreadyExistingRoom.getImage4() != null && !alreadyExistingRoom.getImage4().trim().isEmpty()){
            image_insert4.setImageBitmap(BitmapFactory.decodeFile(alreadyExistingRoom.getImage4()));
            image_insert4.setVisibility(View.VISIBLE);
            currentPhotoPath4 = alreadyExistingRoom.getImage4();
        }

        if(alreadyExistingRoom.getImage5() != null && !alreadyExistingRoom.getImage5().trim().isEmpty()){
            image_insert5.setImageBitmap(BitmapFactory.decodeFile(alreadyExistingRoom.getImage5()));
            image_insert5.setVisibility(View.VISIBLE);
            currentPhotoPath5 = alreadyExistingRoom.getImage5();
        }

        if(alreadyExistingRoom.getImage6() != null && !alreadyExistingRoom.getImage6().trim().isEmpty()){
            image_insert6.setImageBitmap(BitmapFactory.decodeFile(alreadyExistingRoom.getImage6()));
            image_insert6.setVisibility(View.VISIBLE);
            currentPhotoPath6 = alreadyExistingRoom.getImage6();
        }

        if(alreadyExistingRoom.getImage7() != null && !alreadyExistingRoom.getImage7().trim().isEmpty()){
            image_insert7.setImageBitmap(BitmapFactory.decodeFile(alreadyExistingRoom.getImage7()));
            image_insert7.setVisibility(View.VISIBLE);
            currentPhotoPath7 = alreadyExistingRoom.getImage7();
        }

        if(alreadyExistingRoom.getImage8() != null && !alreadyExistingRoom.getImage8().trim().isEmpty()){
            image_insert8.setImageBitmap(BitmapFactory.decodeFile(alreadyExistingRoom.getImage8()));
            image_insert8.setVisibility(View.VISIBLE);
            currentPhotoPath8 = alreadyExistingRoom.getImage8();
        }

        if(alreadyExistingRoom.getImage9() != null && !alreadyExistingRoom.getImage9().trim().isEmpty()){
            image_insert9.setImageBitmap(BitmapFactory.decodeFile(alreadyExistingRoom.getImage9()));
            image_insert9.setVisibility(View.VISIBLE);
            currentPhotoPath9 = alreadyExistingRoom.getImage9();
        }

        if(alreadyExistingRoom.getImage10() != null && !alreadyExistingRoom.getImage10().trim().isEmpty()){
            image_insert10.setImageBitmap(BitmapFactory.decodeFile(alreadyExistingRoom.getImage10()));
            image_insert10.setVisibility(View.VISIBLE);
            currentPhotoPath10 = alreadyExistingRoom.getImage10();
        }

    }

    //Save room class
    private void saveRoom() {

        //makes sure title and first two widgets are not empty
        if (title_room.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Room title can't be empty!", Toast.LENGTH_SHORT).show();
            return;
        } else if (detail_progress.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Room can't be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        //inserts data to database
        final Rooms rooms = new Rooms();
        rooms.setTitle(title_room.getText().toString());
        rooms.setDateTime(date_time.getText().toString());
        rooms.setImage1(currentPhotoPath);
        rooms.setDesc1(detail_progress.getText().toString());
        rooms.setImage2(currentPhotoPath2);
        rooms.setDesc2(detail_progress2.getText().toString());
        rooms.setImage3(currentPhotoPath3);
        rooms.setDesc3(detail_progress3.getText().toString());
        rooms.setImage4(currentPhotoPath4);
        rooms.setDesc4(detail_progress4.getText().toString());
        rooms.setImage5(currentPhotoPath5);
        rooms.setDesc5(detail_progress5.getText().toString());
        rooms.setImage6(currentPhotoPath6);
        rooms.setDesc6(detail_progress6.getText().toString());
        rooms.setImage7(currentPhotoPath7);
        rooms.setDesc7(detail_progress7.getText().toString());
        rooms.setImage8(currentPhotoPath8);
        rooms.setDesc8(detail_progress8.getText().toString());
        rooms.setImage9(currentPhotoPath9);
        rooms.setDesc9(detail_progress9.getText().toString());
        rooms.setImage10(currentPhotoPath10);
        rooms.setDesc10(detail_progress10.getText().toString());

        //sets id of new note from already existing note
        if(alreadyExistingRoom != null){
            rooms.setId(alreadyExistingRoom.getId());
        }

        @SuppressLint("StaticFieldLeak")
        class SaveRoomTask extends AsyncTask<Void,Void,Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                RoomsDatabase.getDatabase(getApplicationContext()).roomDao().insertRoom(rooms);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        }

        new SaveRoomTask().execute();
    }

    //when delete clicked delete dialog alert is showed
    private void showDeleteRoomDialog() {
        if(dialogDeleteRoom == null){
            AlertDialog.Builder builder = new AlertDialog.Builder(CreateNewRoom.this);
            View view = LayoutInflater.from(this).inflate(R.layout.delete_room,
                    findViewById(R.id.layoutDelete));
            builder.setView(view);
            dialogDeleteRoom = builder.create();
            if (dialogDeleteRoom.getWindow() != null){
                dialogDeleteRoom.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
            view.findViewById(R.id.delete_confirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    @SuppressLint("StaticFieldLeak")
                    class DeleteRoomTask extends AsyncTask<Void,Void,Void>{

                        @Override
                        protected Void doInBackground(Void... voids) {
                            RoomsDatabase.getDatabase(getApplicationContext()).roomDao().deleteRoom(alreadyExistingRoom);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            Intent intent = new Intent();
                            intent.putExtra("isRoomDeleted", true);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    }

                    new DeleteRoomTask().execute();
                }
            });

            view.findViewById(R.id.cancel_msg).setOnClickListener(v -> dialogDeleteRoom.dismiss());
        }

        dialogDeleteRoom.show();
    }

    //CODE FROM ANDROID DEVELOPER DOCUMENTATION
    //Image file creation on camera used 1
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    //Image file creation on camera used 2
    private File createImageFile2() throws IOException {
        // Create an image file name 2
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath2 = image.getAbsolutePath();
        return image;
    }

    //Image file creation on camera used 3
    private File createImageFile3() throws IOException {
        // Create an image file name 3
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath3 = image.getAbsolutePath();
        return image;
    }

    //Image file creation on camera used 4
    private File createImageFile4() throws IOException {
        // Create an image file name 4
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath4 = image.getAbsolutePath();
        return image;
    }

    //Image file creation on camera used 5
    private File createImageFile5() throws IOException {
        // Create an image file name 3
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath5 = image.getAbsolutePath();
        return image;
    }

    //Image file creation on camera used 6
    private File createImageFile6() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath6 = image.getAbsolutePath();
        return image;
    }

    //Image file creation on camera used 7
    private File createImageFile7() throws IOException {
        // Create an image file name 2
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath7 = image.getAbsolutePath();
        return image;
    }

    //Image file creation on camera used 8
    private File createImageFile8() throws IOException {
        // Create an image file name 3
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath8 = image.getAbsolutePath();
        return image;
    }

    //Image file creation on camera used 9
    private File createImageFile9() throws IOException {
        // Create an image file name 4
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath9 = image.getAbsolutePath();
        return image;
    }

    //Image file creation on camera used 10
    private File createImageFile10() throws IOException {
        // Create an image file name 3
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath10 = image.getAbsolutePath();
        return image;
    }

    //Starts take picture intent 1
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.memorypalace.provider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAM_REQUEST_CODE);
            }
        }
    }

    //Starts take picture intent 2
    private void dispatchTakePictureIntent2() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile2();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.memorypalace.provider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAM_REQUEST_CODE2);
            }
        }
    }

    //Starts take picture intent 3
    private void dispatchTakePictureIntent3() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile3();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.memorypalace.provider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAM_REQUEST_CODE3);
            }
        }
    }

    //Starts take picture intent 4
    private void dispatchTakePictureIntent4() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile4();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.memorypalace.provider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAM_REQUEST_CODE4);
            }
        }
    }

    //Starts take picture intent 5
    private void dispatchTakePictureIntent5() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile5();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.memorypalace.provider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAM_REQUEST_CODE5);
            }
        }
    }

    //Starts take picture intent 6
    private void dispatchTakePictureIntent6() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile6();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.memorypalace.provider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAM_REQUEST_CODE6);
            }
        }
    }

    //Starts take picture intent 7
    private void dispatchTakePictureIntent7() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile7();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.memorypalace.provider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAM_REQUEST_CODE7);
            }
        }
    }

    //Starts take picture intent 8
    private void dispatchTakePictureIntent8() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile8();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.memorypalace.provider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAM_REQUEST_CODE8);
            }
        }
    }

    //Starts take picture intent 9
    private void dispatchTakePictureIntent9() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile9();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.memorypalace.provider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAM_REQUEST_CODE9);
            }
        }
    }

    //Starts take picture intent 10
    private void dispatchTakePictureIntent10() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile10();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.memorypalace.provider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAM_REQUEST_CODE10);
            }
        }
    }

    //Asks camera permission
    private void requestCameraPermission() {
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, CAM_PERM_CODE);
        } else {
            dispatchTakePictureIntent();
        }
    }

    //Asks Storage access permission
    private void requestStoragePermission() {
        if(ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(CreateNewRoom.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    STORAGE_PERM_CODE);
        } else {
            uploadImage();
        }
    }

    private void uploadImage(){
        Intent upload = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if(upload.resolveActivity(getPackageManager()) != null){
            startActivityForResult(upload, UPLOAD_REQUEST_CODE);
        }
    }

    //checks if camera permissions is granted
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //checks camera permission
        if(requestCode == CAM_PERM_CODE){
            if(grantResults.length < 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                dispatchTakePictureIntent();
            } else {
                Toast.makeText(this, "Turn on camera permission to use this feature", Toast.LENGTH_SHORT).show();
            }
        }

        //checks storage permission
        if(requestCode == STORAGE_PERM_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                uploadImage();
            } else {
                Toast.makeText(this, "Turn on storage access permission to use this feature", Toast.LENGTH_SHORT).show();
            }
        }

    }

    //adds images to image view once activity started
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if camera selected on image_insert
        if (requestCode == CAM_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                File file = new File(currentPhotoPath);
                image_insert.setImageURI(Uri.fromFile(file));
                Log.d("tag", "Absolute Uri of img is " + Uri.fromFile(file));

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(file);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);

            }
        }

        //if upload selected on image_insert
        if (requestCode == UPLOAD_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if(data != null){
                    Uri contentUri = data.getData();
                    if (contentUri != null){
                        try {

                            InputStream inputStream = getContentResolver().openInputStream(contentUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            image_insert.setImageBitmap(bitmap);
                            image_insert.setVisibility(View.VISIBLE);
                            currentPhotoPath = getUriPath(contentUri);
                            Log.d("tag", "Absolute Uri of img is " + currentPhotoPath);


                        } catch (Exception exception) {
                            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }

        //if camera selected on image_insert2
        if (requestCode == CAM_REQUEST_CODE2) {
            if (resultCode == Activity.RESULT_OK) {
                File file = new File(currentPhotoPath2);
                image_insert2.setImageURI(Uri.fromFile(file));
                Log.d("tag", "Absolute Uri of img is " + Uri.fromFile(file));

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(file);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);

            }
        }

        //if upload selected on image_insert2
        if (requestCode == UPLOAD_REQUEST_CODE2) {
            if (resultCode == RESULT_OK) {
                if(data != null){
                    Uri contentUri = data.getData();
                    if (contentUri != null){
                        try {

                            InputStream inputStream = getContentResolver().openInputStream(contentUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            image_insert2.setImageBitmap(bitmap);
                            image_insert2.setVisibility(View.VISIBLE);
                            currentPhotoPath2 = getUriPath(contentUri);
                            Log.d("tag", "Absolute Uri of img is " + currentPhotoPath2);


                        } catch (Exception exception) {
                            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }

        //if camera selected on image_insert3
        if (requestCode == CAM_REQUEST_CODE3) {
            if (resultCode == Activity.RESULT_OK) {
                File file = new File(currentPhotoPath3);
                image_insert3.setImageURI(Uri.fromFile(file));
                Log.d("tag", "Absolute Uri of img is " + Uri.fromFile(file));

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(file);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);

            }
        }

        //if upload selected on image_insert3
        if (requestCode == UPLOAD_REQUEST_CODE3) {
            if (resultCode == RESULT_OK) {
                if(data != null){
                    Uri contentUri = data.getData();
                    if (contentUri != null){
                        try {

                            InputStream inputStream = getContentResolver().openInputStream(contentUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            image_insert3.setImageBitmap(bitmap);
                            image_insert3.setVisibility(View.VISIBLE);
                            currentPhotoPath3 = getUriPath(contentUri);
                            Log.d("tag", "Absolute Uri of img is " + currentPhotoPath3);


                        } catch (Exception exception) {
                            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }

        //if camera selected on image_insert4
        if (requestCode == CAM_REQUEST_CODE4) {
            if (resultCode == Activity.RESULT_OK) {
                File file = new File(currentPhotoPath4);
                image_insert4.setImageURI(Uri.fromFile(file));
                Log.d("tag", "Absolute Uri of img is " + Uri.fromFile(file));

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(file);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);

            }
        }

        //if upload selected on image_insert4
        if (requestCode == UPLOAD_REQUEST_CODE4) {
            if (resultCode == RESULT_OK) {
                if(data != null){
                    Uri contentUri = data.getData();
                    if (contentUri != null){
                        try {

                            InputStream inputStream = getContentResolver().openInputStream(contentUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            image_insert4.setImageBitmap(bitmap);
                            image_insert4.setVisibility(View.VISIBLE);
                            currentPhotoPath4 = getUriPath(contentUri);
                            Log.d("tag", "Absolute Uri of img is " + currentPhotoPath4);


                        } catch (Exception exception) {
                            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }


        //if camera selected on image_insert5
        if (requestCode == CAM_REQUEST_CODE5) {
            if (resultCode == Activity.RESULT_OK) {
                File file = new File(currentPhotoPath5);
                image_insert5.setImageURI(Uri.fromFile(file));
                Log.d("tag", "Absolute Uri of img is " + Uri.fromFile(file));


                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(file);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);

            }
        }

        //if upload selected on image_insert5
        if (requestCode == UPLOAD_REQUEST_CODE5) {
            if (resultCode == RESULT_OK) {
                if(data != null){
                    Uri contentUri = data.getData();
                    if (contentUri != null){
                        try {

                            InputStream inputStream = getContentResolver().openInputStream(contentUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            image_insert5.setImageBitmap(bitmap);
                            image_insert5.setVisibility(View.VISIBLE);
                            currentPhotoPath5 = getUriPath(contentUri);
                            Log.d("tag", "Absolute Uri of img is " + currentPhotoPath5);


                        } catch (Exception exception) {
                            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }

        //if camera selected on image_insert6
        if (requestCode == CAM_REQUEST_CODE6) {
            if (resultCode == Activity.RESULT_OK) {
                File file = new File(currentPhotoPath6);
                image_insert6.setImageURI(Uri.fromFile(file));
                Log.d("tag", "Absolute Uri of img is " + Uri.fromFile(file));

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(file);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);

            }
        }

        //if upload selected on image_insert6
        if (requestCode == UPLOAD_REQUEST_CODE6) {
            if (resultCode == RESULT_OK) {
                if(data != null){
                    Uri contentUri = data.getData();
                    if (contentUri != null){
                        try {

                            InputStream inputStream = getContentResolver().openInputStream(contentUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            image_insert6.setImageBitmap(bitmap);
                            image_insert6.setVisibility(View.VISIBLE);
                            currentPhotoPath6 = getUriPath(contentUri);
                            Log.d("tag", "Absolute Uri of img is " + currentPhotoPath6);


                        } catch (Exception exception) {
                            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }

        //if camera selected on image_insert7
        if (requestCode == CAM_REQUEST_CODE7) {
            if (resultCode == Activity.RESULT_OK) {
                File file = new File(currentPhotoPath7);
                image_insert7.setImageURI(Uri.fromFile(file));
                Log.d("tag", "Absolute Uri of img is " + Uri.fromFile(file));

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(file);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);

            }
        }

        //if upload selected on image_insert7
        if (requestCode == UPLOAD_REQUEST_CODE7) {
            if (resultCode == RESULT_OK) {
                if(data != null){
                    Uri contentUri = data.getData();
                    if (contentUri != null){
                        try {

                            InputStream inputStream = getContentResolver().openInputStream(contentUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            image_insert7.setImageBitmap(bitmap);
                            image_insert7.setVisibility(View.VISIBLE);
                            currentPhotoPath7 = getUriPath(contentUri);
                            Log.d("tag", "Absolute Uri of img is " + currentPhotoPath7);


                        } catch (Exception exception) {
                            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }

        //if camera selected on image_insert8
        if (requestCode == CAM_REQUEST_CODE8) {
            if (resultCode == Activity.RESULT_OK) {
                File file = new File(currentPhotoPath8);
                image_insert8.setImageURI(Uri.fromFile(file));
                Log.d("tag", "Absolute Uri of img is " + Uri.fromFile(file));

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(file);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);

            }
        }

        //if upload selected on image_inser8
        if (requestCode == UPLOAD_REQUEST_CODE8) {
            if (resultCode == RESULT_OK) {
                if(data != null){
                    Uri contentUri = data.getData();
                    if (contentUri != null){
                        try {

                            InputStream inputStream = getContentResolver().openInputStream(contentUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            image_insert8.setImageBitmap(bitmap);
                            image_insert8.setVisibility(View.VISIBLE);
                            currentPhotoPath8 = getUriPath(contentUri);
                            Log.d("tag", "Absolute Uri of img is " + currentPhotoPath8);


                        } catch (Exception exception) {
                            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }

        //if camera selected on image_insert9
        if (requestCode == CAM_REQUEST_CODE9) {
            if (resultCode == Activity.RESULT_OK) {
                File file = new File(currentPhotoPath9);
                image_insert9.setImageURI(Uri.fromFile(file));
                Log.d("tag", "Absolute Uri of img is " + Uri.fromFile(file));

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(file);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);

            }
        }

        //if upload selected on image_insert9
        if (requestCode == UPLOAD_REQUEST_CODE9) {
            if (resultCode == RESULT_OK) {
                if(data != null){
                    Uri contentUri = data.getData();
                    if (contentUri != null){
                        try {

                            InputStream inputStream = getContentResolver().openInputStream(contentUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            image_insert9.setImageBitmap(bitmap);
                            image_insert9.setVisibility(View.VISIBLE);
                            currentPhotoPath9 = getUriPath(contentUri);
                            Log.d("tag", "Absolute Uri of img is " + currentPhotoPath9);


                        } catch (Exception exception) {
                            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }


        //if camera selected on image_insert10
        if (requestCode == CAM_REQUEST_CODE10) {
            if (resultCode == Activity.RESULT_OK) {
                File file = new File(currentPhotoPath10);
                image_insert10.setImageURI(Uri.fromFile(file));
                Log.d("tag", "Absolute Uri of img is " + Uri.fromFile(file));


                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(file);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);

            }
        }

        //if upload selected on image_insert10
        if (requestCode == UPLOAD_REQUEST_CODE10) {
            if (resultCode == RESULT_OK) {
                if(data != null){
                    Uri contentUri = data.getData();
                    if (contentUri != null){
                        try {

                            InputStream inputStream = getContentResolver().openInputStream(contentUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            image_insert10.setImageBitmap(bitmap);
                            image_insert10.setVisibility(View.VISIBLE);
                            currentPhotoPath10 = getUriPath(contentUri);
                            Log.d("tag", "Absolute Uri of img is " + currentPhotoPath10);


                        } catch (Exception exception) {
                            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }

    //gets uploaded pictures file path
    private String getUriPath(Uri contentUri){
        String filePath;
        Cursor cursor = getContentResolver()
                .query(contentUri, null, null, null, null);
        if(cursor == null){
            filePath = contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex("_data");
            filePath = cursor.getString(index);
            cursor.close();
        }
        return filePath;
    }
}