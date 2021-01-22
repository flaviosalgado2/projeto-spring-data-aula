package projeto.spring.data.aula;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.aula.dao.InterfaceSpringDataUser;
import projeto.spring.data.aula.dao.InterfaceTelefone;
import projeto.spring.data.aula.model.Telefone;
import projeto.spring.data.aula.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring-config.xml" })
public class AppSpringDataTest {

	@Autowired
	private InterfaceSpringDataUser interfaceSpringDataUser;

	@Autowired
	private InterfaceTelefone interfaceTelefone;

	@Test
	public void testeInsert() {

		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();

		usuarioSpringData.setEmail("javeiro@java.com.br");
		usuarioSpringData.setIdade(33);
		usuarioSpringData.setLogin("teste123");
		usuarioSpringData.setSenha("123");
		usuarioSpringData.setNome("Lucas Silva");

		try {
			interfaceSpringDataUser.save(usuarioSpringData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testeConsulta() {
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(7L);

		System.out.println(usuarioSpringData.get().getEmail());
		System.out.println(usuarioSpringData.get().getNome());

		for (Telefone telefone : usuarioSpringData.get().getTelefones()) {

			System.out.println(telefone.getNumero());
			System.out.println(telefone.getTipo());
		}
	}

	@Test
	public void testeUpdate() {
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(2L);

		UsuarioSpringData data = usuarioSpringData.get();

		data.setNome("alex edigio");

		interfaceSpringDataUser.save(data);
	}

	@Test
	public void testeDelete() {

		interfaceSpringDataUser.deleteById(1L);
	}

	@Test
	public void testeDelete2() {

		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(4L);

		interfaceSpringDataUser.delete(usuarioSpringData.get());

	}

	@Test
	public void testeConsultaNome() {

		List<UsuarioSpringData> list = interfaceSpringDataUser.buscaPorNome("Fl");

		for (UsuarioSpringData usuarioSpringData : list) {

			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getIdade());
		}
	}

	@Test
	public void testeConsultaNomeParam() {

		UsuarioSpringData usuarioSpringData = interfaceSpringDataUser.buscaPorNomeParam("Flavio cardoso");

		System.out.println(usuarioSpringData.getNome());
		System.out.println(usuarioSpringData.getIdade());

	}

	@Test
	public void testeDeletePorNome() {

		interfaceSpringDataUser.deletePorNome("Flavio cardoso");
	}

	@Test
	public void testeUpdateEmailPorNome() {
		interfaceSpringDataUser.updateEmailPorNome("teste@teste.com.br", "Flavio cardoso");
	}

	@Test
	public void testeInsertTelefone() {

		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(7L);

		Telefone telefone = new Telefone();

		telefone.setTipo("Celular");
		telefone.setNumero("93938348389");
		telefone.setUsuarioSpringData(usuarioSpringData.get());

		interfaceTelefone.save(telefone);
	}
}
