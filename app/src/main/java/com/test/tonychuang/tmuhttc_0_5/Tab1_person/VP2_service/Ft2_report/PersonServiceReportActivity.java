package com.test.tonychuang.tmuhttc_0_5.Tab1_person.VP2_service.Ft2_report;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.dd.processbutton.iml.SubmitProcessButton;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule.MyInitReturnBar;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ProcessBtnGenerator.ProgressGenerator;


/**
 * 參考(Download_on_click_Button-master、SubmitDemo)
 */


public class PersonServiceReportActivity extends AppCompatActivity implements ProgressGenerator.OnCompleteListener {

    private MyInitReturnBar myInitReturnBar;
    private SubmitProcessButton btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_service_report);

        initBar();

        testDownloadBtn();
    }

    @Override
    public void onComplete() {
        btnSend.setEnabled(true);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PersonServiceReportActivity.this, "Open the Report", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * v1
     */
    /**
     *
     */

    private void initBar() {
        myInitReturnBar = new MyInitReturnBar(this, "健康報告書", 0);
    }

    private void testDownloadBtn() {

        final ProgressGenerator progressGenerator = new ProgressGenerator(this);
        btnSend = (SubmitProcessButton) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressGenerator.start(btnSend);
                btnSend.setEnabled(false);
            }
        });

//        final ProcessButton mProcessButton = (ProcessButton) findViewById(R.id.process_button);
//        if (mProcessButton != null) {
//            mProcessButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mProcessButton.setButtonState(ProcessButton.ButtonState.PROCESSING);
//                    new DownloadThread(mProcessButton).start();
//                    mProcessButton.setEnabled(false);
//                }
//            });
//        }
    }



    /**
     * v2
     */
    /**
     *
     */



    /**
     * d1
     */
    /**
     *
     */



    /**
     * d2
     */
    /**
     *
     */





//    private class DownloadThread extends Thread {
//        Handler mHandler = new Handler();
//
//        private ProcessButton mInnerProcessButton;
//
//        public DownloadThread(ProcessButton processButton) {
//            mInnerProcessButton = processButton;
//        }
//
//        @Override
//        public void run() {
//            super.run();
//            while (mInnerProcessButton.getButtonState() == ProcessButton.ButtonState.PROCESSING && mInnerProcessButton.getCurrentProgress() <= 100) {
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mInnerProcessButton.setProcess(mInnerProcessButton.getCurrentProgress() + 5);
//                    }
//                });
//                try {
//                    sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            mHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    mInnerProcessButton.setEnabled(true);
//                    if (mInnerProcessButton.getCurrentProgress() >= 100) {
//                        mInnerProcessButton.setButtonState(ProcessButton.ButtonState.COMPLETE);
//                    }
//                }
//            });
//        }
//    }

    /**
     * 下載code
     */
    /*
    0.
    //github 下載
    https://github.com/arlyxiao/FileDownloaderManager

    //檢查下載目錄是否存在
    checkAndCreateDirectory("/mydownloads");

    //定義要下載的文件名
    public String fileName = "test.jpg";
    public String fileURL = "https://lh4.googleusercontent.com/-HiJOyupc-tQ/TgnDx1_HDzI/AAAAAAAAAWo/DEeOtnRimak/s800/DSC04158.JPG";

    下載參考 code
    http://jackyrong.iteye.com/blog/1336299

    待處理 : 下載檔案時，通知欄要顯示進度與結果
    http://blog.sina.com.cn/s/blog_465918fc0101570u.html
    http://blog.csdn.net/centralperk/article/details/7550294

    1.
    // File url to download
    private static String file_url = "http://atlantis-network.orgfree.com/images/haimann951.jpg";

    2.new DownloadFileFromURL().execute(file_url);

    3.
    //Background Async Task to download file
    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        //Before starting background thread
        //Show Progress Bar Dialog

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //Downloading file in background thread
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                // this will be useful so that you can show a tipical 0-100% progress bar
                int lenghtOfFile = conection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream
                OutputStream output = new FileOutputStream("/sdcard/downloadedfile.jpg");

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress(""+(int)((total*100)/lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        //Updating progress bar
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        //After completing background task
        //Dismiss the progress dialog
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            dismissDialog(progress_bar_type);

            // Displaying downloaded image into image view
            // Reading image path from sdcard
            String imagePath = Environment.getExternalStorageDirectory().toString() + "/downloadedfile.jpg";
            // setting downloaded into image view
            my_image.setImageDrawable(Drawable.createFromPath(imagePath));
            Toast.makeText(getApplicationContext(), ("Downloaded file succesfully!"),
                    Toast.LENGTH_LONG).show();
        }
    }
     */
}
