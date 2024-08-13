package com.coding404.myweb.util;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* 화면에 그려질 pageNation을 계산하는 클래스 */

@Data //getter, setter
public class PageVO {

    private int pageSize = 5;   //페이지네이션 크기

    private Criteria cri;
    private int page;           //현재조회하는 페이지번호 <-- cri
    private int amount;         //현재조회하는 데이터개수 <-- cri
    private int total;          //전체 게시글 수

    private int end;            //마지막페이지 번호
    private int realEnd;        //맨 마지막페이지에 도달했을 때, 재 계산이 들어가는 실제 끝번호
    private int start;          //시작페이지 번호

    private boolean prev;       //이전버튼
    private boolean next;       //다음버튼

    private List<Integer> pageList; //페이지 네이션 번호를 list로 생성

    /* 생성자 - 생성될때 criteria객체, 전체게시글 수를 받는다. */

    public PageVO(Criteria cri, int total) {

        this.cri = cri;
        this.page = cri.getPage();
        this.amount = cri.getAmount();
        this.total = total;

        //끝페이지 번호 계산(1~10번 페이지 조회시 -> 10)
        this.end = (int) Math.ceil(this.page / (double)this.pageSize) * this.pageSize;
        //실제 끝번호 재 계산(총 게시물 개수가 53개 -> 실제끝번호 6, end페이지 10)
        this.realEnd = (int) Math.ceil(this.total / (double) this.amount);
        //end 재 계산(112개 게시물 -> 11~20페이지번호를 볼때는, end=20, realEnd=12)
        if (end > realEnd) this.end = this.realEnd;
        //시작페이지 번호 계산
        this.start = end - this.pageSize + 1;
        if (start < 1) start = 1;

        //이전버튼 활성화 여부
        this.prev = this.start > 1;
        //다음버튼 활성화 여부
        this.next = this.realEnd > this.end;

        //페이지네이션 생성
        this.pageList = IntStream.rangeClosed(this.start, this.end).boxed().collect(Collectors.toList());

    }

}
