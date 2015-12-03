# -*- coding:utf-8 -*-  

import sqlite3

def createTable():
	con = sqlite3.connect("fuck.db")
	cur = con.cursor() 

	sqlFile = open('createTable.sql', 'r')
	sqls = sqlFile.read().split(';')
	for sql in sqls:
		fuck = sql.strip()
		print '-----execute sql----------'
		print fuck
		cur.execute(fuck)
		print '-----finished----------'
		print

	con.commit()

	cur.close()
	con.close()


class Post:

	id = 0

	def __init__(self, id, createTime, memberId, title, content, tag):
		self.id = id
		self.createTime = createTime
		self.memberId = memberId
		self.title = title
		self.content = content
		self.tag = tag

	def show(self):
		print "id: ", self.id
		print "createTime: ", self.createTime
		print "memberId: ", self.memberId
		print "title: ", self.title
		print "content: ", self.content
		print "tag: ", self.tag




def selectAllPosts():
	con = sqlite3.connect("fuck.db")
	cur = con.cursor() 
	cur.execute("select id, createTime, memberId, title, content, tag from post")
	dataSets = cur.fetchall()
	posts = []
	for data in dataSets:
		post = Post(data[0], data[1], data[2], data[3], data[4], data[5])
		posts.append(post)
	cur.close()
	con.close()
	return posts

def selectPost(id):
	con = sqlite3.connect("fuck.db")
	cur = con.cursor() 
	cur.execute("select id, createTime, memberId, title, content, tag from post where id=%d" % id)
	data = cur.fetchone()
	post = Post(data[0], data[1], data[2], data[3], data[4], data[5])
	cur.close()
	con.close()
	return post

def insertPost(post):
	con = sqlite3.connect("fuck.db")
	cur = con.cursor() 
	cur.execute("insert into post (id, createTime, memberId, title, content, tag) values(?,?,?,?,?,?)",\
		(post.id, post.createTime, post.memberId, post.title, post.content, post.tag) )
	con.commit()
	cur.close()
	con.close()

if __name__ == '__main__':
	#createTable()
	posts = selectAllPosts()
	for post in posts:
		post.show()
	
