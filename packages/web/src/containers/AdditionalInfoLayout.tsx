import styles from './containers.module.css'
//
export default function AdditionalInfoLayout() {
  return (
    <>
      <div className="border-[1px] border-black">
        <h3>SIGN UP</h3>
        <p>회원가입을 위해 아래의 항목을 입력해주세요.</p>
      </div>

      <form
        action=""
        className={`border-[1px] border-black w-[50%] ${styles.form}`}
      >
        <div className={styles.input}>Input</div>
        <div className={styles.input}>Input</div>
        <div className={styles.input}>Input</div>
        <div className={styles.input}>Input</div>
        <div className={styles.check}>check</div>
        <button type="button" className={styles.button}>
          회원가입
        </button>
      </form>
    </>
  )
}
