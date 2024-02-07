package com.example.personalinformationsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(@NonNull FragmentManager fm) {
		super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
	}

	@NonNull
	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				return new PersonalFragment();
			case 1:
				return new BankFragment();
			case 2:
				return new DocumentFragment();
			case 3:
				return new PasswordFragment();
			default:
				throw new IllegalArgumentException("Invalid position");
		}
	}

	@Override
	public int getCount() {
		return 4; // Number of tabs
	}

	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
			case 0:
				return "Personal Information";
			case 1:
				return "Bank Information";
			case 2:
				return "Document Information";
			case 3:
				return "Password Information";
			default:
				return null;
		}
	}
}
