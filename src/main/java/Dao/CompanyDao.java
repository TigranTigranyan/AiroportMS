package Dao;

import model.Company;

import java.util.Set;

public interface CompanyDao {
    Company getByID(long id);
    Set<Company> getAll();
    Set<Company> get(int offset, int perPage,String sort);
    Company save(Company passanger);
    Company update(int ig,Company passanger);
    void delete(long companyId);





}
