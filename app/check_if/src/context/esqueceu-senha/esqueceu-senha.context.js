import createGlobalState from "react-create-global-state";

const USUARIO_ESQUECEU_SENHA_KEY = "usuario-esqueceu-senha";

const stateInStorage = localStorage.getItem(USUARIO_ESQUECEU_SENHA_KEY);
const initialState = stateInStorage ? JSON.parse(stateInStorage) : null;

const [_useGlobalUsuario, Provider] = createGlobalState(initialState);

function useGlobalUsuarioEsqueceuSenha() {
  const [_Usuario, _setUsuario] = _useGlobalUsuario();

  function setUsuario(usuario) {
    _setUsuario(usuario);
    localStorage.setItem(USUARIO_ESQUECEU_SENHA_KEY, JSON.stringify(usuario));
  }

  return [_Usuario, setUsuario];
}

export const GlobalUsuarioEsqueceuSenhaProvider = Provider;
export default useGlobalUsuarioEsqueceuSenha;
