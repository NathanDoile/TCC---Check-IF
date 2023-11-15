import "./navbar.component.css";
import { useState, useEffect } from "react";

export function Navbar({ paginaAtual, numeroPaginas, alterarPagina }) {
  const [navBar, setNavBar] = useState([]);

  useEffect(() => {
    setNavBar([]);

    if (numeroPaginas <= 5) {
      for (let i = 0; i < numeroPaginas; i++) {
        if (i === paginaAtual) {
          setNavBar((oldNavBar) => [
            ...oldNavBar,
            <span
              className="botao-navegar pagina-atual"
              onClick={() => {
                alterarPagina(i);
              }}
            >
              {i + 1}
            </span>,
          ]);
        } else {
          setNavBar((oldNavBar) => [
            ...oldNavBar,
            <span
              className="botao-navegar"
              onClick={() => {
                alterarPagina(i);
              }}
            >
              {i + 1}
            </span>,
          ]);
        }
      }
    } else {
      if (paginaAtual === 0) {
        setNavBar([
          <>
            <span
              className="botao-navegar pagina-atual"
              onClick={() => {
                alterarPagina(0);
              }}
            >
              {1}
            </span>
            <span
              className="botao-navegar"
              onClick={() => {
                alterarPagina(1);
              }}
            >
              {2}
            </span>
            <span
              className="botao-navegar"
              onClick={() => {
                alterarPagina(2);
              }}
            >
              {3}
            </span>
            <span
              className="botao-navegar"
              onClick={() => {
                alterarPagina(3);
              }}
            >
              {4}
            </span>
            <span
              className="botao-navegar"
              onClick={() => {
                alterarPagina(4);
              }}
            >
              {5}
            </span>
          </>,
        ]);
      } else if (paginaAtual === 1) {
        setNavBar([
          <>
            <span
              className="botao-navegar"
              onClick={() => {
                alterarPagina(0);
              }}
            >
              {1}
            </span>
            <span
              className="botao-navegar pagina-atual"
              onClick={() => {
                alterarPagina(1);
              }}
            >
              {2}
            </span>
            <span
              className="botao-navegar"
              onClick={() => {
                alterarPagina(2);
              }}
            >
              {3}
            </span>
            <span
              className="botao-navegar"
              onClick={() => {
                alterarPagina(3);
              }}
            >
              {4}
            </span>
            <span
              className="botao-navegar"
              onClick={() => {
                alterarPagina(4);
              }}
            >
              {5}
            </span>
          </>,
        ]);
      } else if (paginaAtual === numeroPaginas - 2) {
        setNavBar([
          <>
            <span
              className="botao-navegar"
              onClick={() => {
                alterarPagina(paginaAtual - 3);
              }}
            >
              {paginaAtual - 2}
            </span>
            <span
              className="botao-navegar"
              onClick={() => {
                alterarPagina(paginaAtual - 2);
              }}
            >
              {paginaAtual - 1}
            </span>
            <span
              className="botao-navegar"
              onClick={() => {
                alterarPagina(paginaAtual - 1);
              }}
            >
              {paginaAtual}
            </span>
            <span
              className="botao-navegar pagina-atual"
              onClick={() => {
                alterarPagina(paginaAtual);
              }}
            >
              {paginaAtual + 1}
            </span>
            <span
              className="botao-navegar"
              onClick={() => {
                alterarPagina(paginaAtual + 1);
              }}
            >
              {paginaAtual + 2}
            </span>
          </>,
        ]);
      } else if (paginaAtual === numeroPaginas - 1) {
        setNavBar([
          <>
            <span
              className="botao-navegar"
              onClick={() => {
                alterarPagina(paginaAtual - 4);
              }}
            >
              {paginaAtual - 3}
            </span>
            <span
              className="botao-navegar"
              onClick={() => {
                alterarPagina(paginaAtual - 3);
              }}
            >
              {paginaAtual - 2}
            </span>
            <span
              className="botao-navegar"
              onClick={() => {
                alterarPagina(paginaAtual - 2);
              }}
            >
              {paginaAtual - 1}
            </span>
            <span
              className="botao-navegar"
              onClick={() => {
                alterarPagina(paginaAtual - 1);
              }}
            >
              {paginaAtual}
            </span>
            <span
              className="botao-navegar pagina-atual"
              onClick={() => {
                alterarPagina(paginaAtual);
              }}
            >
              {paginaAtual + 1}
            </span>
          </>,
        ]);
      } else {
        setNavBar([
          <>
            <span
              className="botao-navegar"
              onClick={() => {
                alterarPagina(paginaAtual - 2);
              }}
            >
              {paginaAtual - 1}
            </span>
            <span
              className="botao-navegar"
              onClick={() => {
                alterarPagina(paginaAtual - 1);
              }}
            >
              {paginaAtual}
            </span>
            <span
              className="botao-navegar pagina-atual"
              onClick={() => {
                alterarPagina(paginaAtual);
              }}
            >
              {paginaAtual + 1}
            </span>
            <span
              className="botao-navegar"
              onClick={() => {
                alterarPagina(paginaAtual + 1);
              }}
            >
              {paginaAtual + 2}
            </span>
            <span
              className="botao-navegar"
              onClick={() => {
                alterarPagina(paginaAtual + 2);
              }}
            >
              {paginaAtual + 3}
            </span>
          </>,
        ]);
      }
    }
  }, [paginaAtual, numeroPaginas]);

  return (
    <nav className="barra-navegacoes">
      {paginaAtual + 1 === 1 ? null : (
        <span
          className="botao-navegar"
          onClick={() => {
            alterarPagina(paginaAtual - 1);
          }}
        >
          Anterior
        </span>
      )}

      {navBar}

      {paginaAtual + 1 < numeroPaginas ? (
        <span
          className="botao-navegar"
          onClick={() => {
            alterarPagina(paginaAtual + 1);
          }}
        >
          Próxima
        </span>
      ) : null}
    </nav>
  );
}
