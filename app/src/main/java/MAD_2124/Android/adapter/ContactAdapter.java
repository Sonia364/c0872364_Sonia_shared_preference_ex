package MAD_2124.Android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.ArrayList;

import MAD_2124.Android.databinding.ContactItemBinding;
import MAD_2124.Android.model.Contact;

public class ContactAdapter extends ArrayAdapter<Contact> {

    ArrayList<Contact> contactList = new ArrayList<>();
    Context context;
    public ContactAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ContactItemBinding binding =  ContactItemBinding.inflate(LayoutInflater.from(context), parent, false);
        Contact model = contactList.get(position);
        binding.name.setText(model.getFirstName());
        binding.email.setText(model.getEmail());
        return binding.getRoot();
    }

    public void setAdapterData(ArrayList<Contact> contacts){
        contactList = contacts;
        notifyDataSetChanged();

    }
}
