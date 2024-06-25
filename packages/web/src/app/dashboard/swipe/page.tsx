import RefreshButton from '@/containers/swipe/RefreshButton'
import SwipeCardWrapper from '@/containers/swipe/SwipeCardWrapper'

export default function page() {
  // TODO: 스와이프 데이터 받아오기

  return (
    <div className="flex-1 py-[40px] flex flex-col justify-center gap-[100px] a">
      <SwipeCardWrapper />
      {/* <RefreshButton /> */}
    </div>
  )
}
