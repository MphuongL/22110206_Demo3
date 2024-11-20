package vn.iostar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iostar.entity.Product;
import vn.iostar.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductServices {

    @Autowired
    private ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Product> listAll() {
        return repo.findAll();
    }

    @Override
    public Product save(Product product) {
        return repo.save(product);
    }

    @Override
    public Product get(Long id) {
        return repo.findById(id).orElse(null); // Trả về null nếu không tìm thấy
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
