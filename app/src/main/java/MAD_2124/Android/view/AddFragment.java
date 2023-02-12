package MAD_2124.Android.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.io.IOException;
import java.util.ArrayList;

import MAD_2124.Android.R;
import MAD_2124.Android.databinding.FragmentAddBinding;
import MAD_2124.Android.helper.SharedPreferenceHelper;
import MAD_2124.Android.model.Contact;

public class AddFragment extends Fragment {

    FragmentAddBinding binding = null;

    ArrayList<Contact> contacts = new ArrayList<>();

    SharedPreferenceHelper sharedPrefHelper;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefHelper = new SharedPreferenceHelper(getContext());
        contacts = sharedPrefHelper.getContacts();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(binding == null){
            binding = FragmentAddBinding.inflate(inflater, container, false);
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.addBtn.setOnClickListener(
                v -> {
                    String firstname = binding.fname.getText().toString();
                    String lastname = binding.lname.getText().toString();
                    String phone = binding.phone.getText().toString();
                    String email = binding.email.getText().toString();
                    Contact contact = new Contact(firstname, lastname, phone, email);
                    contacts.add(contact);

                    try {
                        sharedPrefHelper.saveContacts(contacts);
                        throw new IOException("An error occurred");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Navigation.findNavController(requireActivity(), R.id.fragmentContainer).popBackStack();
                }
        );

    }

}
