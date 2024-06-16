import './card.css'

export interface BackCardProps {
  classFront?: string
  classBack?: string
  front?: React.ReactNode
  back?: React.ReactNode
}

const BackCard = ({ classFront, classBack, front, back }: BackCardProps) => {
  return (
    <div className="flip-card">
      <div className="flip-card-inner">
        <div className={`flip-card-front ${classFront}`}>{front}</div>
        <div className={`flip-card-back ${classBack}`}>{back}</div>
      </div>
    </div>
  )
}

export default BackCard
