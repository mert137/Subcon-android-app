package com.example.fragment.subcontechs;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class OkuFragment extends Fragment {

    PDFView pdfView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.oku_fragment, container, false);

        CommonMovieClass obj = new CommonMovieClass();

        String ipaddr = obj.getUrl();
        String pathtoku = getResources().getString(R.string.pathtoku);
        String OkuUrl = ipaddr + pathtoku + "1.pdf";

        pdfView = view.findViewById(R.id.pdfView);
        new RetrievePDFStream().execute(OkuUrl);

        return view;
    }

    class RetrievePDFStream extends AsyncTask<String,Void,InputStream>
    {

    @Override
        protected InputStream doInBackground(String... strings){
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                if(conn.getResponseCode() == 200)
                {
                    inputStream = new BufferedInputStream(conn.getInputStream());
                }

            }
            catch (IOException e)
            {
                return null;
            }
            return inputStream;
        }

        protected void onPostExecute(InputStream inputStream)
        {
            pdfView.fromStream(inputStream).load();
        }

    }
}






