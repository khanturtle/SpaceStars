/** 만 나이 계산기 */
export function calculateAge(birthDate: string): number {
  const [birthYear, birthMonth, birthDay] = birthDate.split('-').map(Number)

  const currentDate = new Date()
  const currentYear = currentDate.getFullYear()
  const currentMonth = currentDate.getMonth()
  const currentDay = currentDate.getDate()

  let age = currentYear - birthYear
  if (
    currentMonth < birthMonth - 1 ||
    (currentMonth === birthMonth - 1 && currentDay < birthDay)
  ) {
    age--
  }

  return age
}
