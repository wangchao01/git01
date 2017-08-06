package cn.itcast.lucene.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.DirContext;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import cn.itcast.lucene.dao.BookDao;
import cn.itcast.lucene.dao.BookDaoImpl;
import cn.itcast.lucene.pojo.Book;

public class LuceneTest {

	//����Ĳ���
	@Test
	public void testIndex() throws IOException{
		//�ɼ����
		BookDao daoImpl = new BookDaoImpl();
		
		List<Book> listBook = daoImpl.listBook();
		//�����ĵ�����
		ArrayList<Document> documentList = new ArrayList<Document>();
		
		//ѭ��������ݿ�����
		 for (Book book : listBook) {
			Document document = new Document();
			 
			//�ı���
			document.add(new TextField("id",book.getId().toString(),Store.YES));
			document.add(new TextField("name",book.getName().toString(),Store.YES));
			document.add(new TextField("price",book.getPic().toString(),Store.YES));
			document.add(new TextField("pic",book.getPic().toString(),Store.YES));
			document.add(new TextField("desc",book.getDesc().toString(),Store.YES));

			//�ļ����뼯����
			
			documentList.add(document);
		}
		 
		 // ������ ��������ĵ� 分析器
		Analyzer analyzer = new IKAnalyzer();
		
		//Ŀ¼  ������λ��
		Directory open = FSDirectory.open(new File("E:\\LuecneKU\\KUOne"));
		
		//д�����λ����					�汾��  ������
		
		IndexWriterConfig writerConfig = new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);	
	
		//��������д��
		IndexWriter writer = new IndexWriter(open, writerConfig);
	
		
		//��������ļ�
		for (Document document : documentList) {
			
			writer.addDocument(document);
			
		}
		//�ر���Դ
		writer.close();
	}
	

	
}
