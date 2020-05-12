package com.sample.keepsafechild;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckedTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.util.FusedLocationSource;

public class MapViewActivity extends AppCompatActivity implements OnMapReadyCallback { //지도를 띄워주는 클래스
    private static final int ACCESS_LOCATION_PERMISSION_REQUEST_CODE = 1000;//요청권한 코드
    private FusedLocationSource locationSource;// Google Play 서비스의 Fused Location Provider를 사용하는 LocationSource 구현체.

    MarkerActivity markerActivity;
    PolylineActivity polylineActivity;
    DbMainActivity dbMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        MapFragment mapFragment= (MapFragment)getSupportFragmentManager().findFragmentById(R.id.map);//지도 객체 생성
        mapFragment.getMapAsync(this);
        Intent intent = getIntent();
        double latitude = intent.getDoubleExtra("latitude", 0);
        double longitude = intent.getDoubleExtra("longitude", 0);

        TextView location = findViewById(R.id.location);
        location.setText("위도=" + latitude + ", 경도=" + longitude);


    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap){
        FusedLocationSource locationSource = new FusedLocationSource(this,ACCESS_LOCATION_PERMISSION_REQUEST_CODE);
        naverMap.setLocationSource(locationSource);
        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setLocationButtonEnabled(true);

        //트래킹 모드
        CheckedTextView follow = findViewById(R.id.location_tracking_mode_follow);

        follow.setOnClickListener(v -> naverMap.setLocationTrackingMode(LocationTrackingMode.Follow));
 //위치를 따라서 카메라도 같이 움직입니다.

        naverMap.addOnOptionChangeListener(() -> {
            LocationTrackingMode mode = naverMap.getLocationTrackingMode();
            follow.setChecked(mode == LocationTrackingMode.Follow);
            locationSource.setCompassEnabled(mode == LocationTrackingMode.Follow); //나침반
        });
        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);

    }
    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case ACCESS_LOCATION_PERMISSION_REQUEST_CODE: // 접근허가 요청
                locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults);
                return;
        }
    }*/
}