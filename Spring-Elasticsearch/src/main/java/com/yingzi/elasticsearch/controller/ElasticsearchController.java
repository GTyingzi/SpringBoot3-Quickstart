package com.yingzi.elasticsearch.controller;


import com.yingzi.elasticsearch.model.dto.PersonDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yingzi
 * @date 2025/4/14:14:11
 */
@RestController
@RequestMapping("/elasticsearch")
public class ElasticsearchController {

    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchController.class);

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    // 索引中添加数据
    @PostMapping("/save")
    public String save(@RequestBody PersonDto personDto) {
        logger.info("save esDto: {}", personDto);
        PersonDto saveEntity = elasticsearchOperations.save(personDto);
        return saveEntity.getId();
    }

    // 索引中更新数据
    @PutMapping("/update")
    public PersonDto update(@RequestBody PersonDto personDto) {
        if (elasticsearchOperations.exists(personDto.getId(), PersonDto.class)) {
            elasticsearchOperations.update(personDto);
            logger.info("update esDto: {}", personDto);
        } else {
            elasticsearchOperations.save(personDto);
            logger.info("insert esDto: {}", personDto);
        }
        return personDto;
    }

    // 索引中删除数据
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        elasticsearchOperations.delete(id, PersonDto.class);
        logger.info("delete id:{} successful", id);
    }

    // 根据id查询数据
    @GetMapping("/info/{id}")
    public PersonDto getInfo(@PathVariable("id") String id) {
        PersonDto personDto = elasticsearchOperations.get(id, PersonDto.class);
        if (Objects.isNull(personDto)) {
            logger.info("id:{} not found", id);
        } else {
            logger.info("get esDto: {}", personDto);
        }
        return personDto;
    }

    // 获取索引中所有数据
    @GetMapping("/list")
    public List<PersonDto> list() {
        Criteria criteria = new Criteria();
        Query query = new CriteriaQuery(criteria);
        SearchHits<PersonDto> searchHits = elasticsearchOperations.search(query, PersonDto.class);
        return searchHits.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

    // 分页查询索引数据
    @GetMapping("/page")
    public List<PersonDto> page(@RequestParam(value = "pageNum", defaultValue = "1")int pageNum, @RequestParam(value = "pageSize", defaultValue = "10")int pageSize) {
        Criteria criteria = new Criteria();
        PageRequest pageRequest = PageRequest.of(pageNum-1, pageSize);
        Query query = new CriteriaQuery(criteria).setPageable(pageRequest);
        SearchHits<PersonDto> searchHits = elasticsearchOperations.search(query, PersonDto.class);
        List<PersonDto> esDemos = searchHits.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
        return esDemos;
    }
}
