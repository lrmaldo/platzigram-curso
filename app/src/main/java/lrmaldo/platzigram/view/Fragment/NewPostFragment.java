package lrmaldo.platzigram.view.Fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import lrmaldo.platzigram.Constants;
import lrmaldo.platzigram.PlatzigramApplication;
import lrmaldo.platzigram.R;
import lrmaldo.platzigram.model.Post;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewPostFragment extends Fragment {
    ImageView ivPicture;
    Button btnTakePicture;
    String mCurrentPhotoPath;

    static  final  int REQUEST_IMAGE_CAPTURE = 1;




    String mCurrentAbsolutePhotoPath;
    PlatzigramApplication app;
    StorageReference storageReference;
    DatabaseReference postReference;
   

    public NewPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_new_post, container, false);

        ivPicture =  (ImageView) view.findViewById(R.id.ivpicture);
        btnTakePicture = (Button) view.findViewById(R.id.btn_image);

        app = (PlatzigramApplication) getActivity().getApplicationContext();
        storageReference = app.getStorageReference();
        postReference = app.getPostReference();
       
        btnTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });


        return view;
    }

    private void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> resInfoList = getActivity().getPackageManager().queryIntentActivities(takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY);


        if(takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null){
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.d(TAG, "Error al crear imagen");
            }
            // Continua solamente si el archivo fue creado exitosamente
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        "lrmaldo.platzigram",
                        photoFile);

                for (ResolveInfo resolveInfo : resInfoList) {
                    String packageName = resolveInfo.activityInfo.packageName;
                   getActivity().grantUriPermission(packageName, photoURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }



        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Picasso.with(getActivity()).load(mCurrentPhotoPath).into(ivPicture);
            addPictureToGallery();
            uploadFile();
            Toast.makeText(getActivity(), mCurrentPhotoPath, Toast.LENGTH_LONG).show();
        }
    }



    private void uploadFile() {

        File newFile = new File(mCurrentAbsolutePhotoPath);
        Uri contentUri = Uri.fromFile(newFile);

        StorageReference imagesReference = storageReference.child(Constants.FIREBASE_STORAGE_IMAGES + contentUri.getLastPathSegment());

        UploadTask uploadTask = imagesReference.putFile(contentUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Error subiendo la imagen", Toast.LENGTH_LONG).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                String imageUrl = taskSnapshot.getDownloadUrl().toString();
                createNewPost(imageUrl);

            }
        });

    }

    private void createNewPost(String imageUrl) {

        SharedPreferences prefs = getActivity().getSharedPreferences("USER", getActivity().MODE_PRIVATE);
        String email = prefs.getString("email", "Leo");
        String enCodedEmail = email.replace(".", ",");

        Post post = new Post(enCodedEmail, imageUrl, (double) new Date().getTime());

        postReference.push().setValue(post);

        getFragmentManager().popBackStackImmediate();

    }
    private File createImageFile() throws IOException {

        // Crea una nombre para el archivo de imagen en base a la fecha actual
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directorio en el que será almacenado*/
        );

        //Guarda un archivo: Vamos a utilizar este path cuando necesitemos guardar nuestro archivo
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        mCurrentAbsolutePhotoPath = image.getAbsolutePath();

        return image;
    }

    private void addPictureToGallery() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }
}
