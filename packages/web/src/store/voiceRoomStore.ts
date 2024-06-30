import create from 'zustand'

// Zustand 스토어 생성
const useStore = create((set) => ({
  roomNumber: null, // 초기 상태
  setRoomNumber: (number) => set({ roomNumber: number }), // 상태 업데이트 함수
}))

// 클릭 핸들러
const handleClick = (roomNumber) => {
  useStore.getState().setRoomNumber(roomNumber)
}
