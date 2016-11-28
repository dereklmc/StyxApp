import sqlite3
import sys
import glob
import fileinput

def read_word_lists(path):
	paths = glob.glob(path)
	print "reading paths: ", paths
	words = fileinput.input(files=paths)
	for line in words:
		yield line.strip()
	words.close()

def to_insert_value_list(data_itr):
	for data in data_itr:
		yield "('{}')".format(data)

def tee_length(itr, l):
	accum = []
	for i, value in enumerate(itr, 1):
		accum.append(value)
		if i % l == 0:
			yield accum
			accum = []
	if accum:
		yield accum

def insert_words(words_itr, database):
	conn = sqlite3.connect(database)
	c = conn.cursor()
	for values in tee_length(to_insert_value_list(words_itr),50):
		word_values = ",".join(values)
		insert_stmt = "INSERT INTO words (word) values " + word_values
		print insert_stmt
		c.execute(insert_stmt)
	conn.commit()
	conn.close()

def main():
	source_path = sys.argv[1]
	database = sys.argv[2]
	insert_words(read_word_lists(source_path), database)

main()
