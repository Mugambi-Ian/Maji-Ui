package com.nenecorp.majiapp.Interface.MajiUi;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.nenecorp.majiapp.DataModels.InAppMessage;
import com.nenecorp.majiapp.DataModels.Incident;
import com.nenecorp.majiapp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReportIncident extends AppCompatActivity {
    private static final int GALLERY = 1;
    private static final int CAMERA = 10;
    private String uriPath;
    private ArrayList<InAppMessage> messages;
    private IncidentAdapter incidentAdapter;
    private ListView incidentListView;

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            boolean c = ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth);
            while (c) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_incident);
        findViewById(R.id.ARI_btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.ARI_btnCamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });
        final EditText incident = findViewById(R.id.ARI_txtIncident);
        final EditText message = findViewById(R.id.ARI_txtMessage);
        requestMultiplePermissions();
        messages = new ArrayList<>();
        incidentAdapter = new IncidentAdapter(this, R.layout.activity_report_incident, messages);
        incidentListView = findViewById(R.id.ARI_lstIncidents);
        incidentListView.setAdapter(incidentAdapter);
        findViewById(R.id.ARI_btnSendMessage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (incident.getText().length() == 0) {
                    incident.setText("Fill in field.");
                } else {
                    Incident x = new Incident(incident.getText().toString());
                    x.setIncidentDate("11/11/2019");
                    x.setIncidentMessage(message.getText().toString());
                    if (uriPath != null) {
                        x.setIncidentPic(uriPath);
                        uriPath = null;
                    }
                    x.setIncidentTicketNo("LLLLLLL");
                    incidentListView.requestFocus();
                    incident.setText("");
                    message.setText("");
                    messages.add(new InAppMessage(x));
                    messages.add(new InAppMessage("☺ Received"));
                    incidentAdapter.notifyDataSetChanged();
                }

            }
        });

    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallery();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    private void requestMultiplePermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            Toast.makeText(ReportIncident.this, "Enable app permissions on the settings menu", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    private void choosePhotoFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), contentURI);
                    bitmap.setHasAlpha(true);
                    uriPath = String.valueOf(getImageUri(bitmap));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            thumbnail.setHasAlpha(true);
            uriPath = String.valueOf(getImageUri(thumbnail));
        } else {

            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private Uri getImageUri(Bitmap inImage) {
        Uri uri = null;
        try {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = calculateInSampleSize(options, 700, 700);
            options.inJustDecodeBounds = false;
            Bitmap newBitmap = Bitmap.createScaledBitmap(inImage, 700, 700, true);
            File file = new File(getFilesDir(), "MajiImages" + new Random().nextInt() + ".png");
            FileOutputStream out = openFileOutput(file.getName(), Context.MODE_PRIVATE);
            newBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            String realPath = file.getAbsolutePath();
            File f = new File(realPath);
            uri = Uri.fromFile(f);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return uri;

    }

    private class IncidentAdapter extends ArrayAdapter<InAppMessage> {

        public IncidentAdapter(@NonNull Context context, int resource, @NonNull ArrayList<InAppMessage> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View parentView = convertView;
            if (parentView == null) {
                parentView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_report, parent, false);
            }
            InAppMessage x = getItem(position);
            View layoutS = parentView.findViewById(R.id.LIRI_layoutStatus);
            View layoutM = parentView.findViewById(R.id.LIRI_layoutMessage);
            if (x.isStatus()) {
                layoutS.setVisibility(View.VISIBLE);
                layoutM.setVisibility(View.GONE);
                ((TextView) parentView.findViewById(R.id.LIRI_txtStatus)).setText(x.getStatus());
            } else {
                layoutM.setVisibility(View.VISIBLE);
                layoutS.setVisibility(View.GONE);
                ((TextView) parentView.findViewById(R.id.LIRI_txtDate)).setText(x.getIncidentDate());
                ((TextView) parentView.findViewById(R.id.LIRI_txtIncident)).setText(x.getIncidentTitle());
                ((TextView) parentView.findViewById(R.id.LIRI_txtMessage)).setText(x.getIncidentMessage());
                ImageView pic = parentView.findViewById(R.id.LIRI_imageView);
                if (x.getIncidentPic() != null) {
                    pic.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(x.getIncidentPic()).into(pic);
                } else {
                    pic.setVisibility(View.GONE);
                }
            }
            return parentView;
        }
    }
}
