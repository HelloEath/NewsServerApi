package com.glut.news.vo;

public class CommentUserInfo {
private int 	userid	;//10023668
private String 	username	;//蔚蓝色的海洋
	
private String 	face_360;//	https://cs.vmovier.com/Uploads/avatar/2017/06/07/5937b30746e97_180.jpeg

public int getUserid() {
	return userid;
}

public void setUserid(int userid) {
	this.userid = userid;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getFace_360() {
	return face_360;
}

public void setFace_360(String face_360) {
	this.face_360 = face_360;
}

@Override
public String toString() {
	return "CommentUserInfo [userid=" + userid + ", username=" + username + ", face_360=" + face_360 + "]";
}
	
}
