export interface LockProps {
  className?: string
  width?: string
  height?: string
  viewBox?: string
  fill?: string
}

const Lock = ({
  className,
  width = '12',
  height = '15',
  viewBox = '0 0 12 15',
  fill = '#7D12FF',
}: LockProps) => (
  <svg
    className={className}
    xmlns="http://www.w3.org/2000/svg"
    width={width}
    height={height}
    viewBox={viewBox}
    fill="none"
  >
    <path
      d="M9.59016 14.3852H2.40984C1.77096 14.3843 1.15852 14.1302 0.706765 13.6784C0.255009 13.2266 0.000819835 12.6142 0 11.9753V6.53973C0.00072893 5.90082 0.254864 5.28834 0.706639 4.83656C1.15841 4.38479 1.77094 4.13061 2.40984 4.12988H9.59016C10.2291 4.13061 10.8416 4.38479 11.2934 4.83656C11.7451 5.28834 11.9993 5.90082 12 6.53973V11.9753C11.9993 12.6142 11.7451 13.2268 11.2934 13.6786C10.8416 14.1303 10.2291 14.3844 9.59016 14.3852ZM2.40984 5.50694C2.13593 5.50694 1.87323 5.61574 1.67954 5.80943C1.48586 6.00311 1.37705 6.26581 1.37705 6.53973V11.9753C1.37705 12.2492 1.48586 12.5119 1.67954 12.7056C1.87323 12.8993 2.13593 13.0081 2.40984 13.0081H9.59016C9.86407 13.0081 10.1268 12.8993 10.3205 12.7056C10.5141 12.5119 10.6229 12.2492 10.6229 11.9753V6.53973C10.6229 6.26581 10.5141 6.00311 10.3205 5.80943C10.1268 5.61574 9.86407 5.50694 9.59016 5.50694H2.40984Z"
      fill={fill}
    />
    <path
      d="M8.64252 5.50705C8.45991 5.50705 8.28479 5.43454 8.15566 5.30542C8.02654 5.17629 7.95399 5.00113 7.95399 4.81852V3.94618C7.95399 3.42785 7.74809 2.93077 7.38158 2.56425C7.01506 2.19774 6.51794 1.9918 5.99961 1.9918C5.48127 1.9918 4.9842 2.19774 4.61768 2.56425C4.25116 2.93077 4.04527 3.42785 4.04527 3.94618V4.81852C4.04527 5.00113 3.97271 5.17629 3.84359 5.30542C3.71447 5.43454 3.53935 5.50705 3.35674 5.50705C3.17413 5.50705 2.99897 5.43454 2.86985 5.30542C2.74072 5.17629 2.66821 5.00113 2.66821 4.81852V3.94618C2.66821 3.06263 3.01917 2.21523 3.64393 1.59047C4.2687 0.965701 5.11605 0.614746 5.99961 0.614746C6.88316 0.614746 7.73056 0.965701 8.35532 1.59047C8.98009 2.21523 9.33104 3.06263 9.33104 3.94618V4.81852C9.33104 5.00113 9.25853 5.17629 9.12941 5.30542C9.00029 5.43454 8.82512 5.50705 8.64252 5.50705Z"
      fill={fill}
    />
    <path
      d="M5.99968 10.8597C5.81708 10.8597 5.64196 10.7871 5.51283 10.658C5.38371 10.5288 5.31116 10.3537 5.31116 10.1711V8.17852C5.31116 7.99591 5.38371 7.82083 5.51283 7.69171C5.64196 7.56258 5.81708 7.48999 5.99968 7.48999C6.18229 7.48999 6.35745 7.56258 6.48658 7.69171C6.6157 7.82083 6.68821 7.99591 6.68821 8.17852V10.1711C6.68821 10.3537 6.6157 10.5288 6.48658 10.658C6.35745 10.7871 6.18229 10.8597 5.99968 10.8597Z"
      fill={fill}
    />
  </svg>
)

export default Lock