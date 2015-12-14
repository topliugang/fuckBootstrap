# -*- coding:utf-8 -*-  

import sqlite3
import objects


class DBFucker:
		


	def __init__(self):
		self.con = sqlite3.connect("fuck.db")
		self.cur = self.con.cursor() 
		print self.con
		print self.cur
		print "cain"

	def __del__(self):
		self.cur.close()
		self.con.close()

	def createTable(self):
		sqlFile = open('createTable.sql', 'r')
		sqls = sqlFile.read()
		self.cur.executescript(sqls)
		self.con.commit()

	def selectAllPosts(self):
		self.cur.execute("select id, createTime, memberId, title, content, tag from post")
		dataSets = self.cur.fetchall()
		posts = []
		for data in dataSets:
			post = objects.Post(data[0], data[1], data[2], data[3], data[4], data[5])
			posts.append(post)
		return posts

	def selectPost(self, id):
		self.cur.execute("select id, createTime, memberId, title, content, tag from post where id=%d" % id)
		data = self.cur.fetchone()
		post = objects.Post(data[0], data[1], data[2], data[3], data[4], data[5])
		return post

	def insertPost(self, post):
		self.cur.execute("insert into post (id, createTime, memberId, title, content, tag) values(?,?,?,?,?,?)",\
			(post.id, post.createTime, post.memberId, post.title, post.content, post.tag) )
		self.con.commit()
		



if __name__ == '__main__':
	#createTable()
	db = DBFucker()
	posts = db.selectAllPosts()
	for post in posts:
		post.show()
	
	caonima = Post()
