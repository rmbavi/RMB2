package com.example.rmb.entity;


import java.io.Serializable;
import java.util.List;

/**
 * ���Ű�ţ� id�� ��ʶ imageUrl�� ͼƬ����·��
 * 
 * @author Administrator
 * 
 */

public class NewsPaperNumber implements Serializable {
	private int id;
	private String imageUrl;
	private List<NewsPaperTitle> titleList; // �����б�

	public NewsPaperNumber() {
		super();
	}

	public NewsPaperNumber(int id, String imageUrl,
			List<NewsPaperTitle> titleList) {
		super();
		this.id = id;
		this.imageUrl = imageUrl;
		this.titleList = titleList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<NewsPaperTitle> getTitleList() {
		return titleList;
	}

	public void setTitleList(List<NewsPaperTitle> titleList) {
		this.titleList = titleList;
	}

}
