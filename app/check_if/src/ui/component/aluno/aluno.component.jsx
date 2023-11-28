import "./aluno.component.css";
import { useNavigate } from "react-router-dom";

export function Aluno({ nome, turma, matricula, dataNascimento, id }) {

  const navigate = useNavigate();

  return (
    <div className="div-aluno" onClick={() => {navigate(`/aluno/${id}`)}}>
      <span className="aluno-nome">{nome}</span>
      <span className="aluno-turma">{turma}</span>
      <span className="aluno-matricula">{matricula}</span>
      <span className="aluno-data-nascimento">{dataNascimento}</span>
    </div>
  );
}
