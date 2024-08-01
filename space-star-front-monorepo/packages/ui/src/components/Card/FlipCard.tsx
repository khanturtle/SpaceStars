import './card.css'

export interface BackCardProps {
  classFrontName?: string
  classBackName?: string
  front?: React.ReactNode
  back?: React.ReactNode
}

const BackCard = ({
  classFrontName,
  classBackName,
  front,
  back,
}: BackCardProps) => {
  return (
    <div className="flip-card">
      <div className="flip-card-inner">
        <div className={`flip-card-front ${classFrontName}`}>{front}</div>
        <div className={`flip-card-back ${classBackName}`}>{back}</div>
      </div>
    </div>
  )
}

export default BackCard
