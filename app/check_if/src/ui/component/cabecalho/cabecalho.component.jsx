import "./cabecalho.component.css";
import useGlobalUsuario from "../../../context/usuario/usuario.context";
import usuarioImg from "../../../assets/images/UserCirculo-Branco.svg";
import logo from "../../../assets/images/Logo-header.svg";
import logoutImg from "../../../assets/images/Sair-branco.svg";
import lupa from "../../../assets/images/Lupa-marrom.svg";
import home from "../../../assets/images/Home-branco.svg";
import vincular from "../../../assets/images/Vincular-branco.svg";
import notificacao from "../../../assets/images/Notificacao-branco.svg";
import editar from "../../../assets/images/Editar-branco.svg";
import usuarioCadastro from "../../../assets/images/User-branco.svg";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useLogoutHook } from "../../../hooks/login/logout.hook";
import { toast } from "react-toastify";

export function Cabecalho() {
  const navigate = useNavigate();

  const [usuario] = useGlobalUsuario();

  const [mostrarBarraLateral, setMostrarBarraLateral] = useState(false);

  const { logout } = useLogoutHook();

  const [aluno, setAluno] = useState("");

  const [cadastrarUsuario, setCadastrarUsuario] = useState(false);

  function handleChange(event) {
    const { value } = event.target;

    setAluno(value);
  }

  async function onSubmit(event) {
    event.preventDefault();

    if (aluno === "") {
      toast.error("Preencha a barra de pesquisa para que realizar uma.");
    } else {
      navigate(`/pesquisar/${aluno}`);
    }
  }

  return (
    <>
      <header className="cabecalho">
        <img
          src={usuarioImg}
          alt="usuario"
          className="usuario-icone"
          onClick={() => {
            setMostrarBarraLateral(!mostrarBarraLateral);
          }}
        />

        <img
          src={logo}
          alt="Check-IF"
          className="logo-icone"
          onClick={() => {
            navigate("/home");
          }}
        />

        <div className="container-pesquisar-sair">
          {usuario.permissao ? (
            <form className="form-pesquisar" onSubmit={onSubmit}>
              Pesquise o aluno aqui
              <br />
              <label className="label-pesquisar">
                <input
                  type="search"
                  name="aluno"
                  className="input-pesquisar-aluno"
                  onChange={handleChange}
                  required
                />
                <button className="botao-pesquisar">
                  <img src={lupa} alt="Lupa" className="lupa-icone" />
                </button>
              </label>
            </form>
          ) : null}

          <span className="logout" onClick={logout}>
            <img src={logoutImg} alt="Sair" className="logout-icone" />
            <span className="legenda-logout-icone">Sair</span>
          </span>
        </div>
      </header>

      <div
        className={
          mostrarBarraLateral ? "barra-lateral" : "barra-lateral invisivel"
        }
      >
        <span className="dados-perfil">
          <p>Olá, {usuario.nome}!</p>

          <img src={usuarioImg} alt="Usuário" className="dados-perfil-icone" />

          <p>{usuario.email}</p>
        </span>

        <span className="acoes-perfil">
          {usuario.permissao === "ADMINISTRADOR" ? (
            <>
              <button
                className="botao-acao"
                onClick={() => {
                  navigate("/home");
                }}
              >
                <img src={home} alt="Home" className="icone-acao" />
                Página Inicial
              </button>

              <button className="botao-acao">
                <img src={vincular} alt="Vincular" className="icone-acao" />
                Vincular aluno
              </button>

              <button
                className="botao-acao"
                onClick={() => {
                  setCadastrarUsuario(!cadastrarUsuario);
                }}
              >
                <img
                  src={usuarioCadastro}
                  alt="Usuário"
                  className="icone-acao"
                />
                Cadastrar usuário
              </button>

              {cadastrarUsuario ? (
                <span className="botoes-acao-cadastro-usuario">
                  <button
                    className="botao-acao-cadastro-usuario"
                    onClick={() => {
                      navigate("/cadastrar-administrador");
                    }}
                  >
                    Administrador
                  </button>
                  <button
                    className="botao-acao-cadastro-usuario"
                    onClick={() => {
                      navigate("/cadastrar-responsavel");
                    }}
                  >
                    Responsável
                  </button>
                  <button
                    className="botao-acao-cadastro-usuario"
                    onClick={() => {
                      navigate("/cadastrar-responsavel/lote");
                    }}
                  >
                    Responsável em lote
                  </button>
                </span>
              ) : null}

              <button className="botao-acao">
                <img
                  src={notificacao}
                  alt="Notificação"
                  className="icone-acao"
                />
                Gerenciar professores
              </button>

              <button className="botao-acao">
                <img src={editar} alt="Editar" className="icone-acao" />
                Alterar dados pessoais
              </button>
            </>
          ) : null}
        </span>
      </div>
    </>
  );
}
