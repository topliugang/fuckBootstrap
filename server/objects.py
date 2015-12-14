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

