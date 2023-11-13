package com.project.Glog.service;

import com.project.Glog.domain.Blog;
import com.project.Glog.domain.Category;
import com.project.Glog.domain.Post;
import com.project.Glog.dto.request.category.CategoryCreateRequest;
import com.project.Glog.dto.response.category.CategoryDto;
import com.project.Glog.dto.response.category.SidebarDto;
import com.project.Glog.dto.response.category.SidebarDtos;
import com.project.Glog.repository.BlogRepository;
import com.project.Glog.repository.CategoryRepository;
import com.project.Glog.repository.PostRepository;
import com.project.Glog.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private  CategoryRepository categoryRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private PostRepository postRepository;

    public Category create(UserPrincipal userPrincipal, CategoryCreateRequest req){
        Blog blog= blogRepository.findByUserId(userPrincipal.getId()).get();
        Category category = req.toCategory(blog);

        return categoryRepository.save(category);
    }

    public CategoryDto getCategory(Long categoryId){
        Category category = categoryRepository.findById(categoryId).get();
        return CategoryDto.of(category);
    }

    public void delete(Long uid, Long categoryId) {
        //TODO 인가 로직 들어가야함, 예외 처리
        categoryRepository.delete(categoryRepository.findById(categoryId).get());
    }

    public SidebarDtos getSideBarByBlog(Long blogId) {
        //해당 블로그의 카테고리를 모두 불러온다.
        List<Category> categories = categoryRepository.findAllByBlogId(blogId);

        //모든 카테고리를 순회하며, 각 카테고리당 게시글을 모두 불러와서 SidebarDto에 담는다.
        List<SidebarDto> sidebarDtos = new ArrayList<>();
        for(Category category : categories){
            List<Post> posts = postRepository.findAllByCategoryId(category.getId());
            sidebarDtos.add(new SidebarDto(category, posts));
        }

        return new SidebarDtos(sidebarDtos);
    }
}
