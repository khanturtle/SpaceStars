/** 경험치 계산기
 * 경험치통: N**2 + 11 -> N은 레벨
 * N: 1 ~ 10
 */
export function calculateExp(N: number) {
  return N ** 2 + 11
}

/**  exp가 주어졋을 때, 레벨 구하기
 * exp가 너무 작으면 0
 * exp가 더 크면, 10
 */
export function calculateLevel(exp: number) {
  if (exp < 12) return 0
  if (exp > 111) return 10
  for (let i = 1; i <= 10; i++) {
    if (exp <= calculateExp(i)) return i
  }
  return 0
}
