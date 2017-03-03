package comkakashipham05.httpsfacebook.repeattext.Fragment;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import comkakashipham05.httpsfacebook.repeattext.R;

public class MainFragment extends Fragment {
    EditText ed1, ed2, ed3, ed4, edout;
    CheckBox cbox1, radiobt;
    RadioButton radiobt1, radiobt2;
    Button btclear, btaction, btcopy;
    ProgressDialog progressDialog;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();


        //nhu nay, hoac nhu nay
        //hoc cach dat ten bien ten ham luon di

    }

    void initViews() {
        ed1 = (EditText) getView().findViewById(R.id.ed1);
        ed2 = (EditText) getView().findViewById(R.id.ed2);
        ed3 = (EditText) getView().findViewById(R.id.ed3);
        ed4 = (EditText) getView().findViewById(R.id.ed4);
        edout = (EditText) getView().findViewById(R.id.edout);
        btaction = (Button) getView().findViewById(R.id.btaction);
        btcopy = (Button) getView().findViewById(R.id.btcopy);
        btclear = (Button) getView().findViewById(R.id.btclear);
        radiobt = (CheckBox) getView().findViewById(R.id.radiobt);
        cbox1 = (CheckBox) getView().findViewById(R.id.cbox1);
        radiobt1 = (RadioButton) getView().findViewById(R.id.radiobt1);
        radiobt2 = (RadioButton) getView().findViewById(R.id.radiobt2);

        btaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                lamquenThread();
            }
        });
        btclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hanhdongclear();
            }
        });
        btcopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyToClipBoard(edout.getText().toString());
                Toast.makeText(getActivity(), "Đã copy vào bộ nhớ tạm", Toast.LENGTH_SHORT).show();
            }
        });
        radiobt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radiobt1.isChecked() == true) radiobt2.setChecked(false);
            }
        });
        radiobt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radiobt2.isChecked() == true) radiobt1.setChecked(false);
            }
        });
    }

    public void hanhdongclear() {
        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
        ed4.setText("");
        edout.setText("");
        cbox1.setChecked(false);
        radiobt.setChecked(false);
        radiobt1.setChecked(false);
        radiobt2.setChecked(false);
    }
//    private void hanhdongaction(){
//        String text1,text2,text3,text4,text5="",xuongdong="";
//        text1 = ed1.getText().toString().trim();
//        text2 = ed2.getText().toString().trim();
//        int max = Integer.parseInt(text2);
//        if (cbox1.isChecked()==true) xuongdong="\n";
//        if (radiobt.isChecked()==true){
//            text3 = ed3.getText().toString().trim();
//            text4 = ed4.getText().toString().trim();
//            int start = Integer.parseInt(text3);
//            int buocnhay = Integer.parseInt(text4);;
//            if (radiobt1.isChecked()==true){
//                int maxx=buocnhay*max+start;
//                for (;start<maxx;start=start+buocnhay){
//                    String luu=String.valueOf(start);
//                    text5=text5+luu+text1+xuongdong;
//                }}
//            else{
//                int maxx=start-buocnhay*max;
//                for (;start>maxx;start=start-buocnhay){
//                    String luu=String.valueOf(start);
//                    text5=text5+luu+text1+xuongdong;
//                }
//            }
//        } else {
//            for (int start=1;start<max;start=start+1){
//                text5=text5+text1+xuongdong;
//            }
//        }
//        edout.setText(text5);
//    }

    public boolean copyToClipBoard(String text) {
        try {
            int sdk = android.os.Build.VERSION.SDK_INT;
            if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getActivity()
                        .getSystemService(getActivity().CLIPBOARD_SERVICE);
                clipboard.setText(text);
            } else {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getActivity()
                        .getSystemService(getActivity().CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData
                        .newPlainText("copied", text);
                clipboard.setPrimaryClip(clip);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }




    class MyAwesomeAsyncTask extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mProgress;

        @Override
        protected void onPreExecute() {
            mProgress = new ProgressDialog(getActivity());
            mProgress.setCancelable(true);
            mProgress.setMessage("Đang xử lý, vui lòng đợi...");
            mProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgress.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            String text1,text2,text3,text4,text5="",xuongdong="";
            text1 = ed1.getText().toString().trim();
            text2 = ed2.getText().toString().trim();
            int max = Integer.parseInt(text2);
            if (cbox1.isChecked()==true) xuongdong="\n";
            if (radiobt.isChecked()==true){
                text3 = ed3.getText().toString().trim();
                text4 = ed4.getText().toString().trim();
                int start = Integer.parseInt(text3);
                int buocnhay = Integer.parseInt(text4);;
                if (radiobt1.isChecked()==true){
                    int maxx=buocnhay*max+start;
                    for (;start<maxx;start=start+buocnhay){
                        String luu=String.valueOf(start);
                        text5=text5+luu+text1+xuongdong;
                    }}
                else{
                    int maxx=start-buocnhay*max;
                    for (;start>maxx;start=start-buocnhay){
                        String luu=String.valueOf(start);
                        text5=text5+luu+text1+xuongdong;
                    }
                }
            } else {
                for (int start=1;start<max;start=start+1){
                    text5=text5+text1+xuongdong;
                }
            }
            edout.setText(text5);
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            //update your listView adapter here
            //Dismiss your dialog

        }

    }
}


