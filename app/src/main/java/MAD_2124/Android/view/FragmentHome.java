package MAD_2124.Android.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import MAD_2124.Android.R;
import MAD_2124.Android.adapter.ContactAdapter;
import MAD_2124.Android.databinding.FragmentHomeBinding;
import MAD_2124.Android.helper.SharedPreferenceHelper;
import MAD_2124.Android.model.Contact;

public class FragmentHome extends Fragment {

    FragmentHomeBinding binding = null;

    ContactAdapter adapter;

    ArrayList<Contact> contacts = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadSharePreferenceData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(binding == null){
            binding = FragmentHomeBinding.inflate(inflater,container, false);

        }

        return binding.getRoot();

    }

    @Override
    public void onResume() {
        super.onResume();
        loadSharePreferenceData();
        adapter.setAdapterData(contacts);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adapter =  new ContactAdapter(requireContext(), R.layout.contact_item);
        binding.contactList.setAdapter(adapter);
        adapter.setAdapterData(contacts);

        binding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String s) {
                filterData(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterData(s);
                return true;
            }
        });

        binding.addContact.setOnClickListener(
                v -> {
                    Navigation.findNavController(requireActivity(), R.id.fragmentContainer).navigate(R.id.action_fragmentHome_to_addFragment);
                }
        );


    }

    public void filterData(String s){
        if(!s.isEmpty()){
            ArrayList<Contact> filter = new ArrayList<>();
            contacts.forEach(contact -> {
                if(contact.getFirstName().contains(s)){
                    filter.add(contact);
                }
            });
            adapter.setAdapterData(filter);
        }else{
            adapter.setAdapterData(contacts);
        }

    }

    private void loadSharePreferenceData(){
        SharedPreferenceHelper sharedPrefHelper = new SharedPreferenceHelper(getContext());
        contacts = sharedPrefHelper.getContacts();
    }


}
