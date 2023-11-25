import "./pagina-nao-encontrada.screen.css";
import { Borda, TituloTelasIniciais, Botao } from "../../component";
import useGlobalUsuario from "../../../context/usuario/usuario.context";
import { useNavigate } from "react-router-dom";

export function TelaPaginaNaoEncontrada() {
  const [usuario] = useGlobalUsuario();

  const navigate = useNavigate();

  function paginaInicial() {
    if (usuario) {
      navigate("/home");
    } else {
      navigate("/");
    }
  }

  return (
    <>
      <Borda posicao="superior" />

      <main className="main-pagina-nao-encontrada">
        <h1 className="status-erro">404</h1>

        <TituloTelasIniciais>
          Desculpe, a página que você está procurando não foi encontrada.
        </TituloTelasIniciais>

        <div className="container-sugestoes">
          <b>Você pode tentar o seguinte:</b>

          <ul>
            <li>Verifique se a URL foi digitada corretamente.</li>
            <li>Volte à página inicial.</li>
          </ul>
        </div>

        <Botao cor="verde" onClick={paginaInicial}>
          Página inicial
        </Botao>
      </main>

      <Borda posicao="inferior" />
    </>
  );
}
