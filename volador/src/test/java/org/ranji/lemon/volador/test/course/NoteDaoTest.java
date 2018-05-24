package org.ranji.lemon.volador.test.course;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.volador.VoladorApplication;
import org.ranji.lemon.volador.model.course.Note;
import org.ranji.lemon.volador.service.course.prototype.INoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=VoladorApplication.class)  //-- 指定Spring-Boot的启动类
public class NoteDaoTest {
	@Autowired
	private INoteService noteservice;
//	@Test
//	public void addNote(){
//		Note note = new Note();
//		note.setContent("It is first content./n");
//		noteservice.save(note);
//		System.out.print(note.getContent());
//	}
	
//	@Test
//	public void findNote(){
//		Note note = noteservice.find(1);
//		System.out.print(note.getContent());
//	}
	
//	@Test
//	public void findAllNote(){
//		List<Note> notes = noteservice.findAll();
//		System.out.print(notes.toString());
//	}
//	
//	@Test
//	public void updateNote(){
//		List<Note> notes = noteservice.findAll();
//		String after = notes.get(0).getContent();
//		notes.get(0).setContent("modify conment.");
//		noteservice.update(notes.get(0));
//		System.out.print(after +" 修改为 " + notes.get(0).getContent());
//	}
	
//	@Test
//	public void deleteNote(){
//		List<Note> notes = noteservice.findAll();
//		noteservice.delete(notes.get(0).getId());
//		System.out.print("删除：" + notes.get(0).getContent());
//	}
	@Test
	public void addNoteAndUserRelation(){
//		Note note = new Note();
//		note.setContent("It is second content./n");
//		noteservice.save(note);
//		
		System.out.print(noteservice.findNoteByUserId(40).toString());
		//noteservice.deleteNoteAndUserRelationByNoteId(2);
		noteservice.delete(2);
//		noteservice.saveNoteAndUserRelation(2, 40);
//		System.out.print(note.toString());
	}
}
