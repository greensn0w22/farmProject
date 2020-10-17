package julien.farmproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private EditText datePicked;
    private Button syncToCloud;
    private Button saveTonnage;
    private Button datePickerOpener;
    private DBManager dbManager;
    private TextView nbToSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbManager = new DBManager(this);
        dbManager.open();
//        Cursor cursor = dbManager.fetch();

        AndroidNetworking.initialize(getApplicationContext());


        API_CALL_GET_USERS();


        Spinner dropdown = findViewById(R.id.sp_user_selected);
        String[] items = fetchUsersForDropDown();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


        syncToCloud = (Button) findViewById(R.id.bt_syncToCloud);
        syncToCloud.setOnClickListener(this);
        saveTonnage = (Button) findViewById(R.id.bt_save_tonnage);
        saveTonnage.setOnClickListener(this);
        datePickerOpener = (Button) findViewById(R.id.bt_date_picker);
        datePickerOpener.setOnClickListener(this);
        datePicked = (EditText) findViewById(R.id.ed_date_picker);
        nbToSend = (TextView) findViewById(R.id.nbToSend);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_syncToCloud:
                syncTonnagesToCloud();
                break;
            case R.id.bt_save_tonnage:
                saveTonnageLocal();
                break;
            case R.id.bt_date_picker:
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this, MainActivity.this, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            default:
                break;
        }
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        datePicked.setText(format2Digits(dayOfMonth) + "-" + format2Digits(month) + "-" + year);
    }

    private String format2Digits(int toFormat) {
//       TODO REFACTORING TO FLEX WITH TERNARY EXPRESSION :P

        if (toFormat < 10)
            return "0" + toFormat;
        else
            return toFormat + "";
    }

    private void saveTonnageLocal() {

        dbManager.insertTonnage(1, 1, "2020-07-07", 1);
        refreshView();
    }

    private void refreshView() {
        try {
            nbToSend.setText(dbManager.countTonnagesNotSended());
        } catch (Exception e) {
            Log.v("cobol", e.toString());
        }
    }

    private void syncTonnagesToCloud() {
    }

    private void API_CALL_GET_USERS() {
        AndroidNetworking.get("https://fierce-cove-29863.herokuapp.com/getAllUsers/{pageNumber}")
                .addPathParameter("pageNumber", "0")
                .addQueryParameter("limit", "3")
                .addHeaders("token", "1234")
                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    private String[] fetchUsersForDropDown() {

        return new String[]{"COBOL", "CoBoL", "cObOl"};
    }
}