package com.acampdev.borisalexandrcamposrios.ampay.Fragments;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.acampdev.borisalexandrcamposrios.ampay.Manifest;
import com.acampdev.borisalexandrcamposrios.ampay.R;

public class Soporte extends Fragment {

    private  static  final int REQUEST_CALL= 1;
    private  static  final int REQUEST_SEND=1;
    TextView number,mensaje,correoEmpresa,ubicacionEmpresa;
    ImageView call,sms,email, gps;
    CardView phone, message, correo, empresa;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_soporte,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        number=(TextView) view.findViewById(R.id.phoneNumberID);
        mensaje=(TextView) view.findViewById(R.id.messageID);
        correoEmpresa=(TextView) view.findViewById(R.id.emailContactID);

        phone=(CardView) view.findViewById(R.id.cardViewCall);
        message=(CardView) view.findViewById(R.id.cardViewSms);
        correo=(CardView) view.findViewById(R.id.cardViewEmail);
        empresa=(CardView) view.findViewById(R.id.cardViewEmpresa);

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneSms();
            }
        });

        correo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeEmail();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void makePhoneCall(){
        String num = number.getText().toString();
        if(num.trim().length()>0){
            if(ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(getActivity(),new String[]{android.Manifest.permission.CALL_PHONE},REQUEST_CALL);
            }else{
                String dial="tel:"+num;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }else{
            Toast.makeText(getContext(),"Numero Invalido",Toast.LENGTH_SHORT).show();
        }
    }

    public void makePhoneSms(){

        try{
            String sms = mensaje.getText().toString();
            if(sms.trim().length()>0){
                if(ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(),new  String[] {android.Manifest.permission.SEND_SMS},REQUEST_SEND);
                }else{
                    Intent sendIntend = new Intent(Intent.ACTION_VIEW);
                    sendIntend.setData(Uri.parse("sms:"+sms));
                    sendIntend.putExtra("body","este es una prueba message");
                    startActivity(sendIntend);
                }
            }else {
                Toast.makeText(getContext(),"Numero Invalido",Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){e.printStackTrace();}

    }

    public void makeEmail(){
        try{
            Intent sendEmail= new Intent(Intent.ACTION_SEND);
            sendEmail.setType("text/plain");
            sendEmail.putExtra(Intent.EXTRA_SUBJECT,"rcavasquezg@gmail.com");
            sendEmail.putExtra(Intent.EXTRA_TEXT,"");
            startActivity(sendEmail);
        }catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode== REQUEST_CALL){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }else{
                Toast.makeText(getContext(),"Permission DENIED",Toast.LENGTH_SHORT).show();
            }
        }
        // SEND SMS
        if(requestCode== REQUEST_SEND){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneSms();
            }else{
                Toast.makeText(getContext(),"Permission DENIED",Toast.LENGTH_SHORT).show();
            }
        }

    }




}
