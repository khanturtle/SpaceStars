import { useRef, useState } from 'react'

interface UseDragReturn {
  isDragging: boolean
  handleMouseDown: (e: React.MouseEvent<HTMLDivElement>) => void
  handleMouseUp: (e: React.MouseEvent<HTMLDivElement>) => void
  handleMouseMove: (e: React.MouseEvent<HTMLDivElement>) => void
  divRef: React.RefObject<HTMLDivElement>
}

const useDrag = (): UseDragReturn => {
  const ref = useRef<HTMLDivElement>(null)
  const div = ref.current
  const refId = useRef<number | null>(null)
  const [isDragging, setIsDragging] = useState(false)
  const [previousX, setPreviousX] = useState(0)

  const handleMouseDown = (e: React.MouseEvent<HTMLDivElement>) => {
    setIsDragging(true)
    setPreviousX(e.clientX)
  }

  const handleMouseUp = (e: React.MouseEvent<HTMLDivElement>) => {
    setIsDragging(false)
  }

  const handleMouseMove = (e: React.MouseEvent<HTMLDivElement>) => {
    if (!isDragging || !div || refId.current) {
      return
    }

    refId.current = requestAnimationFrame(() => {
      if (div) {
        const delta = e.clientX - previousX
        div.scrollLeft -= delta
        setPreviousX(e.clientX)
      }
      refId.current = null
    })
  }

  return {
    isDragging,
    handleMouseDown,
    handleMouseUp,
    handleMouseMove,
    divRef: ref,
  }
}

export default useDrag
