from flask import Flask, render_template, jsonify, request

app = Flask(__name__)

from pymongo import MongoClient

client = MongoClient('mongodb://test:test@localhost', 27017)

# db = client.


## HTML 화면 보여주기
@app.route('/')
def main2page():
    return render_template('main2page.html')


# 주문하기(POST) API
@app.route('/login', methods=['POST'])
def save_order():
    name_receive = request.form['name_give']
    num_receive = request.form['num_give']
    add_receive = request.form['add_give']
    phone_receive = request.form['phone_give']

    doc = {
        'name': name_receive,
        'num': num_receive,
        'add': add_receive,
        'phone': phone_receive
    }

    #db.shop.insert_one(doc)
    return jsonify({'msg': '주문완료!'})




if __name__ == '__main__':
    app.run('0.0.0.0', port=5000, debug=True)