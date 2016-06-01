package com.suwonsmartapp.android4ki.listview.retrofit;

import com.suwonsmartapp.android4ki.listview.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by junsuk on 16. 6. 1..
 */
public interface ContactAPI {

    String BASE_URL = "http://suwonsmartapp.iptime.org/test/twoong/retrofit/";

    @GET("getProfile.php")
    Call<List<Contact>> getContacts();
}
