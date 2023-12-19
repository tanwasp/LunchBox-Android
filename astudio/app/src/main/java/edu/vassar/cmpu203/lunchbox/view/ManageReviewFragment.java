import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import edu.vassar.cmpu203.lunchbox.R;
import edu.vassar.cmpu203.lunchbox.view.IManageReviewView;

public class ManageReviewFragment extends Fragment implements IManageReviewView {

    private final Context context;
    private final View rootView;
    private final BottomSheetDialog bottomSheetDialog;
    private IManageReviewView.Listener listener;

    public interface Listener {
        void onEditReviewSelected();
        void onDeleteReviewSelected();
    }

    public ManageReviewFragment(Context context) {
        this.context = context;
        this.rootView = LayoutInflater.from(context).inflate(R.layout.fragment_manage_review, null);
        this.bottomSheetDialog = new BottomSheetDialog(context);
        initializeViews();
    }

    private void initializeViews() {
        TextView editOption = rootView.findViewById(R.id.editOption);
        TextView deleteOption = rootView.findViewById(R.id.deleteOption);

        editOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onEditReviewSelected();
                }
                bottomSheetDialog.dismiss();
            }
        });

        deleteOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onDeleteReviewSelected();
                }
                bottomSheetDialog.dismiss();
            }
        });
    }

    public void setListener(IManageReviewView.Listener listener) {
        this.listener = listener;
    }

    public void showOptionsDialog() {
        bottomSheetDialog.setContentView(rootView);
        bottomSheetDialog.show();
    }

    public void dismissOptionsDialog() {
        bottomSheetDialog.dismiss();
    }
}
