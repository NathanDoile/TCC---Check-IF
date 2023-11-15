import "./chegada-atrasada.component.css";

export function ChegadaAtrasada({ nome, turma, matricula, data, hora }) {
  return (
    <div className="div-chegada-atrasada">
      <span className="chegada-atrasada-nome">{nome}</span>
      <span className="chegada-atrasada-turma">{turma}</span>
      <span className="chegada-atrasada-matricula">{matricula}</span>
      <span className="chegada-atrasada-data">{data}</span>
      <span className="chegada-atrasada-hora">{hora}</span>
    </div>
  );
}
