function comparedToday(date: Date): string {
  const today = new Date()
  const yesterday = new Date(today.getTime() - 24 * 60 * 60 * 1000)

  if (isSameDay(date, today)) {
    return date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })
  } else if (isSameDay(date, yesterday)) {
    return '어제'
  } else {
    return date.toLocaleDateString()
  }
}

function isSameDay(date1: Date, date2: Date): boolean {
  return (
    date1.getFullYear() === date2.getFullYear() &&
    date1.getMonth() === date2.getMonth() &&
    date1.getDate() === date2.getDate()
  )
}

export function convertToKoreanTime(dateString: string | Date): string {
  let date: Date
  if (typeof dateString === 'string') {
    date = new Date(dateString)
  } else if (dateString instanceof Date) {
    date = dateString
  } else {
    return ''
  }
  const formattedDate = comparedToday(date)

  return formattedDate
}

export function getConvertToKoreanHM(dateString: string | Date): string {
  let date: Date
  if (typeof dateString === 'string') {
    date = new Date(dateString)
  } else if (dateString instanceof Date) {
    date = dateString
  } else {
    return ''
  }

  return date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })
}
