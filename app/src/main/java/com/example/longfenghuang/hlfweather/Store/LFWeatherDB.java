package com.example.longfenghuang.hlfweather.Store;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.longfenghuang.hlfweather.Model.City;
import com.example.longfenghuang.hlfweather.Model.County;
import com.example.longfenghuang.hlfweather.Model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longfenghuang on 16/4/29.
 */
public class LFWeatherDB {

    public static final String DBName = "LF_Weather"; //数据库名称
    public static final int DBVersion = 1;            //数据库版本
    private static LFWeatherDB lfWeatherDB;
    private SQLiteDatabase db;

    /**
     * 构造方法私有化
     * @param context context
     */
    private LFWeatherDB(Context context)
    {
        LFWeatherOpenHelper dbHelper = new LFWeatherOpenHelper(context,DBName,null,DBVersion);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 获取LFWeatherDB 实例
     * @param context context
     * @return 实例
     */
    public synchronized static LFWeatherDB getInstance(Context context) {

        if (lfWeatherDB == null)
        {
            lfWeatherDB = new LFWeatherDB(context);
        }

        return lfWeatherDB;
    }

    /**
     * 将province 实例存储到数据库
     * @param province 省份数据
     */
    public void saveProvince(Province province) {
        if (province != null)
        {
            ContentValues values = new ContentValues();
            values.put("province_name",province.getProvinceName());
            values.put("province_code",province.getProvinceCode());
            db.insert("Province",null,values);
        }
    }

    /**
     * 从数据库中读取全国所有的省份信息
     * @return 省份列表
     */
    public List<Province> loadProvince() {

        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("Province",null,null,null,null,null,null);

        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            } while (cursor.moveToNext());
        }

        return list;

    }

    /**
     * 将city实例存储到数据库
     * @param city 实例
     */
    public void saveCity(City city) {

        if (city != null) {
            ContentValues values = new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityCode());
            values.put("province_id",city.getProvinceId());
            db.insert("City",null,values);
        }
    }

    /**
     * 从数据库中读取某省下所有的城市信息
     * @param provinceId 省份id
     * @return 城市列表
     */
    public List<City> loadCities(int provinceId) {

        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("City", null, "province_id = ?",
                new String[] { String.valueOf(provinceId) }, null, null, null); if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor
                        .getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor
                        .getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            } while (cursor.moveToNext());
        }
        return list;
    }

    /**
     * 将County实例存储到数据库。
     *
     */
    public void saveCounty(County county) {
        if (county != null) {
            ContentValues values = new ContentValues();
            values.put("county_name", county.getCountyName());
            values.put("county_code", county.getCountyCode());
            values.put("city_id", county.getCityId());
            db.insert("County", null, values);
        }
    }

    /**
     * 从数据库读取某城市下所有的县信息
     * @param cityId 城市id
     * @return 县列表
     */
    public List<County> loadCounties(int cityId) {
        List<County> list = new ArrayList<County>();
        Cursor cursor = db.query("County", null, "city_id = ?",
                new String[] { String.valueOf(cityId) }, null, null, null); if (cursor.moveToFirst()) {
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor
                        .getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor
                        .getColumnIndex("county_code")));
                county.setCityId(cityId);
                list.add(county);
            } while (cursor.moveToNext());
        }
        return list;
    }



}
