package com.sample.keepsafechild;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.CircleOverlay;
import com.naver.maps.map.overlay.Marker;

public class MarkerActivity  extends AppCompatActivity implements OnMapReadyCallback {
    SeekBar sizeBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);


//        Utmk utmk = new Utmk(953935,1952044.1);
//        LatLng latLng = utmk.toLatLng();
//
//        Toast.makeText(this,"위도: "+latLng.latitude + "경도: " +latLng.longitude,
//                Toast.LENGTH_LONG).show();//들어갔을때 위도 경도 표시
    }


    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Marker marker1 = new Marker();
        marker1.setPosition(new LatLng(1, 1));//일단 마커 위치지정
        marker1.setMap(naverMap);//마커 생성
        CircleOverlay markerCircle = new CircleOverlay();//원 생성

        naverMap.setOnMapClickListener((point, coord)-> { //지도 화면클릭시
            Toast.makeText(this, getString(R.string.format_map_click, coord.latitude, coord.longitude), Toast.LENGTH_SHORT).show();
            marker1.setPosition(new LatLng(coord.latitude, coord.longitude));//클릭 좌표로 마커 위치 이동
            markerCircle.setCenter(coord);//원 중심을 선택한 위치로
            markerCircle.setRadius(300);//원 반지름
            markerCircle.setColor(0x4000FFFF);//불투명도 지정
            markerCircle.setMap(naverMap);
            System.out.println(coord.latitude);
            System.out.println(coord.longitude);//로그로 위도 경도 출력 test
        });

//        FusedLocationSource locationSource = new FusedLocationSource(this, 100);
//        naverMap.setLocationSource(locationSource);
//        UiSettings uiSettings = naverMap.getUiSettings();
//        uiSettings.setLocationButtonEnabled(true);

    }
}
