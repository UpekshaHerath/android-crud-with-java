package com.example.tha_app_204074m.data.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tha_app_204074m.data.entity.ItemInfo;
import com.example.tha_app_204074m.data.repositories.ItemInfoRepo;

import java.util.List;

public class ItemInfoViewModel extends AndroidViewModel {

    // creating a new variable for course repository.
    private ItemInfoRepo repo;

    // below line is to create a variable for live
    // data where all the courses are present.
    private LiveData<List<ItemInfo>> allItems;

    public ItemInfoViewModel(@NonNull Application application) {
        super(application);
        repo = new ItemInfoRepo(application);
        allItems = repo.getAllItems();
    }

    // below method is use to insert the data to our repository.
    public void insert(ItemInfo model) {
        repo.insert(model);
    }

    public void update(ItemInfo model){
        repo.update(model);
    }

    public void delete(ItemInfo model){
        repo.delete(model);
    }

    // below method is to get all the courses in our list.
    public LiveData<List<ItemInfo>> getAllItems() {
        return allItems;
    }
}
