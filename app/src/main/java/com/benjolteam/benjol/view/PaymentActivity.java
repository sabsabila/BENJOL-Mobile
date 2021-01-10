package com.benjolteam.benjol.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.benjolteam.android_example.R;
import com.benjolteam.benjol.contract.PaymentContract;
import com.benjolteam.android_example.databinding.ActivityPaymentDetailBinding;
import com.benjolteam.benjol.interactor.PaymentInteractor;
import com.benjolteam.benjol.model.Payment;
import com.benjolteam.benjol.presenter.PaymentPresenter;
import com.benjolteam.benjol.util.UtilProvider;

import java.text.NumberFormat;
import java.util.Locale;

public class PaymentActivity extends AppCompatActivity implements PaymentContract.View, View.OnClickListener{
    private PaymentContract.presenter presenter;
    private ActivityPaymentDetailBinding binding;
    private int bookingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        bookingId = intent.getIntExtra("booking_id", 0);

        presenter = new PaymentPresenter(this, new PaymentInteractor(UtilProvider.getSharedPreferencesUtil()));
        initView();
    }

    private void initView(){
        presenter.setPayment(bookingId);
        binding.backButton.setOnClickListener(this);
        binding.navbar.homeButton.setOnClickListener(this);
        binding.navbar.profileButton.setOnClickListener(this);
        binding.pageTitle.setText("Payment Detail");
        binding.transferButton.setOnClickListener(this);
    }

    @Override
    public void startLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.backButton.getId()){
            onBackButtonClick();
        }
        if(v.getId() == binding.navbar.homeButton.getId()){
            onHomeButtonClick();
        }
        if(v.getId() == binding.navbar.profileButton.getId()){
            onProfileClick();
        }
        if(v.getId() == binding.transferButton.getId()){
            onTransferClick();
        }
    }

    private void onTransferClick() {
        Intent intent = new Intent(this, UploadReceiptActivity.class);
        intent.putExtra("booking_id", bookingId);
        finish();
        startActivity(intent);
    }

    private void onHomeButtonClick() {
        finish();
        startActivity(new Intent(this, DashboardActivity.class));
    }

    private void onProfileClick() {
        finish();
        startActivity(new Intent(this, ProfileActivity.class));
    }

    public void onBackButtonClick(){
        Intent intent = new Intent(this, ProgressServiceActivity.class);
        intent.putExtra("booking_id", bookingId);
        finish();
        startActivity(intent);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPayment(Payment payment) {
        if(payment != null){
            binding.setPayment(payment);
            binding.paymentId.setText(Integer.toString(payment.getPayment_id()));
            Locale localeID = new Locale("in", "ID");
            NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
            binding.paymentCost.setText("Rp. " + formatRupiah.format(payment.getService_cost()).substring(2));
            if(payment.getStatus().equalsIgnoreCase("paid")){
                binding.transferButton.setBackgroundResource(R.drawable.button_disabled);
                binding.transferButton.setTextColor(getResources().getColor(R.color.colorPrimary));
                binding.transferButton.setEnabled(false);
            }
        }
    }
}
