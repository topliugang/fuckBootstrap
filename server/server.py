import tornado.ioloop
import tornado.web

class Post:
	def __init__(self, id, title, content, author):
		this.id = id
		this.title = title
		this.content = content
		this.author = author





class MainHandler(tornado.web.RequestHandler):
    def get(self):
        self.write("You requested the main page fuckkkkkk")


class ListHandler(tornado.web.RequestHandler):
	def get(self):
		
		post1 = Post(1, "first post", "this is first post  hehehe  caoniam", 22)
		post2 = Post(2, "second post", "this is 2 post  hehehe  caoniam", 22)
		post3 = Post(3, "third post", "this is 3 post  hehehe  caoniam", 22)




		items = ["Item 1", "Item 2", "Item 3"]
		self.render("list.html", title="My title", items=items)




class StoryHandler(tornado.web.RequestHandler):
    def get(self, story_id):
        self.write("You requested the story " + story_id)



application = tornado.web.Application([
    (r"/", MainHandler),
    (r"/list", ListHandler),
    (r"/post/([0-9]+)", StoryHandler),
    (r"/toWrite", ToWriteHandler),
    (r"/write", WriteHandler),
])



if __name__ == "__main__":
    application.listen(80)
    tornado.ioloop.IOLoop.instance().start()