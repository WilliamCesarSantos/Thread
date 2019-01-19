package com.santos.will.search.engine.dto;

/**
 * Utilizado para transferencia de informação
 * http://www.devmedia.com.br/diferenca-entre-os-patterns-po-pojo-bo-dto-e-vo/28162
 * 
 * @author William
 * @since 2016-10-25
 * @version 1.0.0
 */
public class Person {

	private Long idPerson;
	private String name;
	private Integer age;
	private String profession;

	public Long getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Long idPerson) {
		this.idPerson = idPerson;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

}
