package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Admin;
import com.example.demo.repositories.AdminRepository;

@Component
public class AdminServices
{
	@Autowired
	private AdminRepository adminRepository;

	public List<Admin> getAll()
	{
		return (List<Admin>) this.adminRepository.findAll();
	}

	public Admin getAdmin(int id)
	{
		Optional<Admin> optional = this.adminRepository.findById(id);
		return optional.orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
	}

	public void update(Admin admin, int id)
	{
		admin.setAdminId(id);
		this.adminRepository.save(admin);
	}

	public void delete(int id)
	{
		this.adminRepository.deleteById(id);
	}

	public void addAdmin(Admin admin)
	{
		this.adminRepository.save(admin);
	}

	public boolean validateAdminCredentials(String email, String password)
	{
		if (email == null || password == null) return false;
		Admin admin = adminRepository.findByAdminEmail(email);
		return admin != null && admin.getAdminPassword() != null && admin.getAdminPassword().equals(password);
	}
}
