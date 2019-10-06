package alfahmi.mynote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.werdpressed.partisan.rundo.RunDo;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity implements RunDo.TextLink {

    EditText TextField;
    Button Undo, Redo;
    RunDo mRundo;
    CoordinatorLayout myCoordinatorLayout;
    String mynote = "MyNote";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myCoordinatorLayout = (CoordinatorLayout)findViewById(R.id.alfahmi_layout) ;
        TextField = (EditText) findViewById(R.id.alfahmi_text_field);

        mRundo = RunDo.Factory.getInstance(getSupportFragmentManager());
        try {
            FileInputStream fileInputStream = openFileInput(mynote);
            byte[] readBytes = new byte[fileInputStream.available()];
            fileInputStream.read(readBytes);
            TextField.setText(new String(readBytes));
            fileInputStream.close();
        } catch (Exception e) {
        }
    }

    public void onPause() {
        super.onPause();
        SaveData();
    }

    public EditText getEditTextForRunDo()
    {
        return (TextField);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.alfahmi_action_undo:
                mRundo.undo();
                break;
            case R.id.alfahmi_action_redo:
                mRundo.redo();
                break;
            case R.id.alfahmi_action_copy:
                if (TextField.getText().toString() == null) {
                    Snackbar snackbar = Snackbar.make(myCoordinatorLayout,"TEXT KOSONG", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("text", TextField.getText().toString());
                    clipboard.setPrimaryClip(clip);
                    Snackbar snackbar = Snackbar.make(myCoordinatorLayout,"TEXT COPY, OK!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void SaveData() {
        try {
            FileOutputStream fileOutputStream = openFileOutput(mynote, 0);
            fileOutputStream.write(TextField.getText().toString().getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
        }
    }
}