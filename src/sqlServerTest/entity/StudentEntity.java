package sqlServerTest.entity;

public class StudentEntity {

	private String name;//学生姓名
	private String age;//学生年龄
	private String sex;//学生性别
	
	public StudentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentEntity(String name, String age, String sex) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "StudentEntity [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
	
	
	
}
